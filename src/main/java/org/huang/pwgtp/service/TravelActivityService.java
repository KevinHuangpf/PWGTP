package org.huang.pwgtp.service;

import com.alibaba.fastjson.JSON;
import org.huang.pwgtp.convertor.TravelActivityConvertor;
import org.huang.pwgtp.repository.TravelActivityMapper;
import org.huang.pwgtp.repository.model.TravelActivityDO;
import org.huang.pwgtp.service.model.TravelActivityDTO;
import org.huang.pwgtp.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class TravelActivityService {

    @Autowired
    private TravelActivityMapper travelActivityMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelActivityConvertor travelActivityConvertor;


    public Long createTravelActivity(TravelActivityDTO travelActivityDTO){
        travelActivityDTO.setHasRecruitedMemberList(Arrays.asList(travelActivityDTO.getCreatorUid()));
        TravelActivityDO travelActivityDO = travelActivityConvertor.convertTravelActivityDTOToDO(travelActivityDTO);
        return travelActivityMapper.insert(travelActivityDO);
    }

    public void editTravelActivity(TravelActivityDTO travelActivityDTO) throws Exception{
        if(Objects.isNull(travelActivityDTO.getId())){
            throw new Exception("记录不存在，无法操作");
        }
        TravelActivityDO travelActivityDO = travelActivityConvertor.convertTravelActivityDTOToDO(travelActivityDTO);
        int updateResult = travelActivityMapper.updateById(travelActivityDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public TravelActivityDTO getTravelActivityById(Long id){
        TravelActivityDO travelActivityDO = travelActivityMapper.getById(id);
        return travelActivityConvertor.convertTravelActivityDOToDTO(travelActivityDO);
    }

    public void joinTravelActivity(Long travelActivityId, Long userId) throws Exception{
        TravelActivityDTO travelActivityDTO = this.getTravelActivityById(travelActivityId);
        if(Objects.isNull(travelActivityDTO)){
            throw new Exception("出行记录不存在，无法操作");
        }
        UserDTO userDTO = userService.getUserById(userId);
        if(Objects.isNull(userDTO)){
            throw new Exception("用户不存在，无法操作");
        }
        travelActivityDTO.getHasRecruitedMemberList().add(userId);
        TravelActivityDO travelActivityDONew = new TravelActivityDO();
        travelActivityDONew.setId(travelActivityId);
        travelActivityDONew.setHasRecruitedMemberList(JSON.toJSONString(travelActivityDTO.getHasRecruitedMemberList()));
        int updateResult = travelActivityMapper.updateById(travelActivityDONew);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public void updateTravelActivityStatus(Long travelActivityId, String status) throws Exception{
        TravelActivityDO travelActivityDO = travelActivityMapper.getById(travelActivityId);
        if(Objects.isNull(travelActivityDO)){
            throw new Exception("记录不存在，无法操作");
        }
        int updateResult = travelActivityMapper.updateStatusById(travelActivityId, status);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

}
