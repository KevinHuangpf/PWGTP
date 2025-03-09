package org.huang.pwgtp.service;

import org.huang.pwgtp.convertor.UserConvertor;
import org.huang.pwgtp.repository.UserMapper;
import org.huang.pwgtp.repository.model.UserDO;
import org.huang.pwgtp.service.model.UserDTO;
import org.huang.pwgtp.service.model.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConvertor userConvertor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(String username, String password, String telephone)throws Exception{
        UserDO userDOExist= userMapper.getByName(username);
        if(Objects.nonNull(userDOExist)){
            throw new Exception("用户已存在");
        }
        UserDO userDO = new UserDO();
        userDO.setName(username);
        userDO.setMobilePhone(telephone);
        userDO.setPassword(passwordEncoder.encode(password));
        int updateResult = userMapper.insert(userDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public UserDTO getUserById(Long id){
        UserDO userDO = userMapper.getById(id);
        return userConvertor.convertDOToDTO(userDO);
    }

    public UserDTO getCurrentUser() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
        if(Objects.isNull(userDetailsDTO)){
            throw new Exception("用户不存在");
        }
        return userDetailsDTO.getUserDTO();
    }

    public List<UserDTO> listUserById(List<Long> ids){
        List<UserDO> userDOList = userMapper.listByIds(ids);
        return userConvertor.convertDOToDTOList(userDOList);
    }


    public UserDetails loadUserByUsername(String userName){
        UserDO userDO = userMapper.getByName(userName);
        if(Objects.isNull(userDO)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetailsDTO(userConvertor.convertDOToDTO(userDO));
    }


}
