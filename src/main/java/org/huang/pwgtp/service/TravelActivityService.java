package org.huang.pwgtp.service;

import org.huang.pwgtp.convertor.TravelActivityConvertor;
import org.huang.pwgtp.repository.TravelActivityMapper;
import org.huang.pwgtp.repository.model.TravelActivityDO;
import org.huang.pwgtp.service.model.TravelActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelActivityService {

    @Autowired
    private TravelActivityMapper travelActivityMapper;

    @Autowired
    private TravelActivityConvertor travelActivityConvertor;


    public Long createTravelActivity(TravelActivityDTO travelActivityDTO){
        TravelActivityDO travelActivityDO = travelActivityConvertor.convertTravelActivityDTOToDO(travelActivityDTO);
        return travelActivityMapper.insert(travelActivityDO);
    }

    public void editTravelActivity(TravelActivityDTO travelActivityDTO){
        TravelActivityDO travelActivityDO = travelActivityConvertor.convertTravelActivityDTOToDO(travelActivityDTO);
        travelActivityMapper.updateByPrimaryKey(travelActivityDO);
    }

    public TravelActivityDTO getTravelActivityById(Long id){
        TravelActivityDO travelActivityDO = travelActivityMapper.selectById(id);
        return travelActivityConvertor.convertTravelActivityDOToDTO(travelActivityDO);
    }



}
