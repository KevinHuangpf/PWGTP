package org.huang.pwgtp.convertor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.huang.pwgtp.repository.model.TravelActivityDO;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.TravelActivityDTO;
import org.huang.pwgtp.controller.vo.TravelActivityDetailVO;
import org.huang.pwgtp.controller.vo.TravelActivitySaveVO;
import org.huang.pwgtp.controller.vo.UserVO;
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
        TravelActivityDO travelActivityDO = new TravelActivityDO();
        BeanUtils.copyProperties(travelActivityDTO, travelActivityDO);
        travelActivityDO.setHasRecruitedMemberList(JSON.toJSONString(travelActivityDTO.getHasRecruitedMemberList()));
        return travelActivityDO;
    }

    public TravelActivityDTO convertTravelActivitySaveVOToDTO(TravelActivitySaveVO travelActivitySaveVO){
        TravelActivityDTO travelActivityDTO = new TravelActivityDTO();
        BeanUtils.copyProperties(travelActivitySaveVO, travelActivityDTO);
        return travelActivityDTO;
    }

    public TravelActivityDTO convertTravelActivityDOToDTO(TravelActivityDO travelActivityDO){
        TravelActivityDTO travelActivityDTO = new TravelActivityDTO();
        BeanUtils.copyProperties(travelActivityDO, travelActivityDTO);
        travelActivityDTO.setHasRecruitedMemberList(JSON.parseObject(travelActivityDO.getHasRecruitedMemberList(), new TypeReference<List<Long>>(){}));
        return travelActivityDTO;
    }

    public TravelActivityDetailVO convertTravelActivityDTOToVO(TravelActivityDTO travelActivityDTO){
        TravelActivityDetailVO travelActivityDetailVO = new TravelActivityDetailVO();
        BeanUtils.copyProperties(travelActivityDTO, travelActivityDetailVO);

        UserVO creatorUser = userConvertor.convertDTOToVO(userService.getUserById(travelActivityDTO.getCreatorUid()));
        travelActivityDetailVO.setCreatorUser(creatorUser);

        List<UserVO> userVOS = userConvertor.convertDOToVOList(userService.listUserById(travelActivityDTO.getHasRecruitedMemberList()));
        travelActivityDetailVO.setHasRecruitedMemberList(userVOS);
        travelActivityDetailVO.setHasRecruitedMemberNumber(CollectionUtils.isEmpty(travelActivityDTO.getHasRecruitedMemberList())? 1: travelActivityDTO.getHasRecruitedMemberList().size()+1);

        return travelActivityDetailVO;
    }


    public List<TravelActivityDetailVO> convertTravelActivityDTOToVOList(List<TravelActivityDTO> travelActivityDTOList){
        if(CollectionUtils.isEmpty(travelActivityDTOList)){
            return new ArrayList<>();
        }
        return travelActivityDTOList.stream().map(this::convertTravelActivityDTOToVO).collect(Collectors.toList());
    }

}
