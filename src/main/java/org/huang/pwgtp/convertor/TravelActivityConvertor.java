package org.huang.pwgtp.convertor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.huang.pwgtp.repository.model.TravelActivityDO;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.TravelActivityDTO;
import org.huang.pwgtp.controller.vo.TravelActivityDetailVO;
import org.huang.pwgtp.controller.vo.TravelActivitySaveVO;
import org.huang.pwgtp.controller.vo.UserVO;
import org.huang.pwgtp.service.model.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravelActivityConvertor {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertor userConvertor;


    public TravelActivityDO convertTravelActivityDTOToDO(TravelActivityDTO travelActivityDTO){
        if(travelActivityDTO == null){
            return null;
        }
        TravelActivityDO travelActivityDO = new TravelActivityDO();
        BeanUtils.copyProperties(travelActivityDTO, travelActivityDO);
        travelActivityDO.setHasRecruitedMemberList(JSON.toJSONString(travelActivityDTO.getHasRecruitedMemberList()));
        return travelActivityDO;
    }

    public TravelActivityDTO convertTravelActivitySaveVOToDTO(TravelActivitySaveVO travelActivitySaveVO){
        if(travelActivitySaveVO == null){
            return null;
        }
        TravelActivityDTO travelActivityDTO = new TravelActivityDTO();
        BeanUtils.copyProperties(travelActivitySaveVO, travelActivityDTO);
        return travelActivityDTO;
    }

    public TravelActivityDTO convertTravelActivityDOToDTO(TravelActivityDO travelActivityDO){
        if(travelActivityDO == null){
            return null;
        }
        TravelActivityDTO travelActivityDTO = new TravelActivityDTO();
        BeanUtils.copyProperties(travelActivityDO, travelActivityDTO);
        travelActivityDTO.setHasRecruitedMemberList(JSON.parseObject(travelActivityDO.getHasRecruitedMemberList(), new TypeReference<List<Long>>(){}));
        travelActivityDTO.setTravelWay(JSON.parseObject(travelActivityDO.getTravelWay(), new TypeReference<List<String>>(){}));
        return travelActivityDTO;
    }

    public TravelActivityDetailVO convertTravelActivityDTOToVO(TravelActivityDTO travelActivityDTO){
        if(travelActivityDTO == null){
            return null;
        }
        TravelActivityDetailVO travelActivityDetailVO = new TravelActivityDetailVO();
        BeanUtils.copyProperties(travelActivityDTO, travelActivityDetailVO);

        UserVO creatorUser = userConvertor.convertDTOToVO(userService.getUserById(travelActivityDTO.getCreatorUid()));
        travelActivityDetailVO.setCreatorUser(creatorUser);

        List<UserDTO> userDTOList = CollectionUtils.isEmpty(travelActivityDTO.getHasRecruitedMemberList())? new ArrayList<>():userService.listUserById(travelActivityDTO.getHasRecruitedMemberList());
        travelActivityDetailVO.setHasRecruitedMemberList(userConvertor.convertDOToVOList(userDTOList));

        travelActivityDetailVO.setHasRecruitedMemberNumber(travelActivityDTO.getHasRecruitedMemberList().size());

        return travelActivityDetailVO;
    }


    public List<TravelActivityDetailVO> convertTravelActivityDTOToVOList(List<TravelActivityDTO> travelActivityDTOList){
        if(CollectionUtils.isEmpty(travelActivityDTOList)){
            return new ArrayList<>();
        }
        return travelActivityDTOList.stream().map(this::convertTravelActivityDTOToVO).collect(Collectors.toList());
    }

}
