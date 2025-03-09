package org.huang.pwgtp.convertor;

import org.huang.pwgtp.repository.model.UserDO;
import org.huang.pwgtp.service.model.UserDTO;
import org.huang.pwgtp.controller.vo.UserVO;
import org.huang.pwgtp.util.ConvertUtil;
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
        return ConvertUtil.convert(UserDTO.class, userDO);
    }

    public List<UserDTO> convertDOToDTOList(List<UserDO> userDOList) {
        return userDOList.stream().map(this::convertDOToDTO).collect(Collectors.toList());
    }



    public UserVO convertDTOToVO(UserDTO userDTO) {
        if(userDTO == null){
            return null;
        }
        return ConvertUtil.convert(UserVO.class, userDTO);
    }

    public List<UserVO> convertDOToVOList(List<UserDTO> userDTOList) {
        if(CollectionUtils.isEmpty(userDTOList)){
            return new ArrayList<>();
        }
        return userDTOList.stream().map(this::convertDTOToVO).collect(Collectors.toList());
    }

}
