package org.huang.pwgtp.convertor;

import org.huang.pwgtp.repository.model.UserDO;
import org.huang.pwgtp.service.model.UserDTO;
import org.huang.pwgtp.controller.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConvertor {

    public UserDTO convertDOToDTO(UserDO userDO) {
        if(userDO == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    public List<UserDTO> convertDOToDTOList(List<UserDO> userDOList) {
        List<UserDTO> userDTOList = userDOList.stream().map(this::convertDOToDTO).collect(Collectors.toList());
        return userDTOList;
    }



    public UserVO convertDTOToVO(UserDTO userDTO) {
        if(userDTO == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, userVO);
        return userVO;
    }

    public List<UserVO> convertDOToVOList(List<UserDTO> userDTOList) {
        if(CollectionUtils.isEmpty(userDTOList)){
            return new ArrayList<>();
        }
        return userDTOList.stream().map(this::convertDTOToVO).collect(Collectors.toList());
    }

}
