package org.huang.pwgtp.convertor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.huang.pwgtp.common.bizEnum.TravelStatusEnum;
import org.huang.pwgtp.common.bizEnum.TravelTypeEnum;
import org.huang.pwgtp.common.bizEnum.TravelWayEnum;
import org.huang.pwgtp.repository.model.TravelDO;
import org.huang.pwgtp.service.EvaluationService;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.controller.vo.TravelDetailVO;
import org.huang.pwgtp.controller.vo.TravelSaveVO;
import org.huang.pwgtp.controller.vo.UserVO;
import org.huang.pwgtp.service.model.UserDTO;
import org.huang.pwgtp.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TravelConvertor {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertor userConvertor;

    @Autowired
    private EvaluationService evaluationService;


    public TravelDO convertTravelDTOToDO(TravelDTO travelDTO){
        if(travelDTO == null){
            return null;
        }
        TravelDO travelDO = ConvertUtil.convert(TravelDO.class, travelDTO);
        travelDO.setHasRecruitedMemberList(CollectionUtils.isEmpty(travelDTO.getHasRecruitedMemberList())? null: JSON.toJSONString(travelDTO.getHasRecruitedMemberList()));
        travelDO.setTravelWay(JSON.toJSONString(travelDTO.getTravelWayList()));
        return travelDO;
    }

    public TravelDTO convertTravelSaveVOToDTO(TravelSaveVO travelSaveVO){
        if(travelSaveVO == null){
            return null;
        }
        return ConvertUtil.convert(TravelDTO.class, travelSaveVO);
    }

    public TravelDTO convertTravelDOToDTO(TravelDO travelDO){
        if(travelDO == null){
            return null;
        }
        TravelDTO travelDTO = ConvertUtil.convert(TravelDTO.class, travelDO);
        travelDTO.setHasRecruitedMemberList(JSON.parseObject(travelDO.getHasRecruitedMemberList(), new TypeReference<Set<Long>>(){}));
        travelDTO.setTravelWayList(JSON.parseObject(travelDO.getTravelWay(), new TypeReference<List<String>>(){}));
        return travelDTO;
    }

    public List<TravelDTO> convertTravelDOToDTOList(List<TravelDO> travelDOList){
        if(CollectionUtils.isEmpty(travelDOList)){
            return new ArrayList<>();
        }
        return travelDOList.stream().map(this::convertTravelDOToDTO).collect(Collectors.toList());
    }

    public TravelDetailVO convertTravelDTOToVO(TravelDTO travelDTO){
        if(travelDTO == null){
            return null;
        }
        TravelDetailVO travelDetailVO = ConvertUtil.convert(TravelDetailVO.class, travelDTO);
        travelDetailVO.setStatusDesc(TravelStatusEnum.getDescByName(travelDTO.getStatus()));
        travelDetailVO.setTravelTypeDesc(TravelTypeEnum.getDescByName(travelDTO.getTravelType()));
        travelDetailVO.setTravelWayDescList(travelDTO.getTravelWayList().stream().map(TravelWayEnum::getDescByName).toList());
        UserVO creatorUser = userConvertor.convertDTOToVO(userService.getUserById(travelDTO.getCreatorUid()));
        travelDetailVO.setCreator(creatorUser);

        List<UserDTO> userDTOList = CollectionUtils.isEmpty(travelDTO.getHasRecruitedMemberList())? new ArrayList<>():userService.listUserById(new ArrayList<>(travelDTO.getHasRecruitedMemberList()));
        travelDetailVO.setHasRecruitedMemberList(userConvertor.convertDOToVOList(userDTOList));
        travelDetailVO.setHasRecruitedMemberNumber(travelDTO.getHasRecruitedMemberList().size());

        return travelDetailVO;
    }


    public List<TravelDetailVO> convertTravelDTOToVOList(List<TravelDTO> travelDTOList){
        if(CollectionUtils.isEmpty(travelDTOList)){
            return new ArrayList<>();
        }
        return travelDTOList.stream().map(this::convertTravelDTOToVO).collect(Collectors.toList());
    }

}
