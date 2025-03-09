package org.huang.pwgtp.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.bizEnum.TravelStatusEnum;
import org.huang.pwgtp.convertor.TravelConvertor;
import org.huang.pwgtp.repository.TravelMapper;
import org.huang.pwgtp.repository.model.TravelDO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.service.model.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class TravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelConvertor travelConvertor;


    public void createTravel(TravelDTO travelDTO) throws Exception{
        travelDTO.setHasRecruitedMemberList(Set.of(travelDTO.getCreatorUid()));
        TravelDO travelDO = travelConvertor.convertTravelDTOToDO(travelDTO);
        travelDO.setModifierUid(travelDO.getCreatorUid());
        travelDO.setStatus(TravelStatusEnum.DRAFT.name());
        int updateResult = travelMapper.insert(travelDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public void editTravel(TravelDTO travelDTO) throws Exception{
        if(Objects.isNull(travelDTO.getId())){
            throw new Exception("行程不存在，无法操作");
        }
        TravelDO travelDO = travelConvertor.convertTravelDTOToDO(travelDTO);
        int updateResult = travelMapper.updateById(travelDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public TravelDTO getTravelById(Long id){
        TravelDO travelDO = travelMapper.getById(id);
        return travelConvertor.convertTravelDOToDTO(travelDO);
    }


    public CommonPage<TravelDTO> listTravelByPage(Integer pageNum, Integer pageSize, String fuzzyKey){
        PageHelper.startPage(pageNum, pageSize).setOrderBy("gmt_create desc");
        List<TravelDO> travelDOList = travelMapper.list(fuzzyKey);
        CommonPage<TravelDO> commonPageDO = CommonPage.restPage(travelDOList);
        CommonPage<TravelDTO> commonPageDTO = new CommonPage<>();
        BeanUtils.copyProperties(commonPageDO, commonPageDTO);
        commonPageDTO.setList(travelConvertor.convertTravelDOToDTOList(commonPageDO.getList()));
        return commonPageDTO;
    }




    public void joinTravel(Long travelId, Long userId) throws Exception{
        // todo 并发问题 redis

        TravelDTO travelDTO = this.getTravelById(travelId);
        if(Objects.isNull(travelDTO)){
            throw new Exception("行程不存在，无法操作");
        }
        if(travelDTO.getHasRecruitedMemberList().size() >= travelDTO.getPlanRecruitMemberNumber()){
            throw new Exception("行程已满员，无法操作");
        }
        travelDTO.getHasRecruitedMemberList().add(userId);

        TravelDO travelDONew = new TravelDO();
        travelDONew.setId(travelId);
        travelDONew.setHasRecruitedMemberList(JSON.toJSONString(travelDTO.getHasRecruitedMemberList()));
        int updateResult = travelMapper.updateById(travelDONew);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public void updateTravelStatus(Long travelId, String status) throws Exception{
        TravelDO travelDO = travelMapper.getById(travelId);
        if(Objects.isNull(travelDO)){
            throw new Exception("行程不存在，无法操作");
        }
        int updateResult = travelMapper.updateStatusById(travelId, status);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }


    public void deleteTravel(Long travelId) throws Exception{
        TravelDO travelDO = travelMapper.getById(travelId);
        if(Objects.isNull(travelDO)){
            throw new Exception("行程不存在，无法操作");
        }
        int updateResult = travelMapper.deleteById(travelId);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }
    
}
