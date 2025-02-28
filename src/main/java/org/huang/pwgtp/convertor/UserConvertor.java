package org.huang.pwgtp.convertor;

import org.huang.pwgtp.repository.model.UserDO;
import org.huang.pwgtp.service.model.UserDTO;
import org.huang.pwgtp.controller.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConvertor {

    public UserDTO convertDOToDTO(UserDO userDO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    public List<UserDTO> convertDOToDTOList(List<UserDO> userDOList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserDO userDO : userDOList){
            userDTOList.add(this.convertDOToDTO(userDO));
        }
        return userDTOList;
    }



    public UserVO convertDTOToVO(UserDTO userDTO) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, userVO);
        return userVO;
    }

    public List<UserVO> convertDOToVOList(List<UserDTO> userDTOList) {
        List<UserVO> userVOList = userDTOList.stream().map(this::convertDTOToVO).collect(Collectors.toList());
        return userVOList;
    }

}
