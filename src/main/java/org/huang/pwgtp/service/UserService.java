package org.huang.pwgtp.service;

import org.huang.pwgtp.convertor.UserConvertor;
import org.huang.pwgtp.repository.UserMapper;
import org.huang.pwgtp.repository.model.UserDO;
import org.huang.pwgtp.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userService;

    @Autowired
    private UserConvertor userConvertor;

    public UserDTO getUserById(Long id){
        UserDO userDO = userService.getById(id);
        return userConvertor.convertDOToDTO(userDO);
    }

    public List<UserDTO> listUserById(List<Long> ids){
        List<UserDO> userDOList = userService.listByIds(ids);
        return userConvertor.convertDOToDTOList(userDOList);
    }


}
