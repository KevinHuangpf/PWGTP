package org.huang.pwgtp.convertor;

import org.huang.pwgtp.common.bizEnum.FavoriteTypeEnum;
import org.huang.pwgtp.controller.vo.FavoriteVO;
import org.huang.pwgtp.service.TravelService;
import org.huang.pwgtp.service.model.FavoriteDTO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FavoriteConvertor {

    @Autowired
    private TravelService travelService;

    public List<FavoriteVO> convertFavoriteDTOToVOList(List<FavoriteDTO> favoriteDTOList) {

        if(CollectionUtils.isEmpty(favoriteDTOList)){
            return new ArrayList<>();
        }
        List<FavoriteVO> favoriteVOList = new ArrayList<>();
        for(FavoriteDTO favoriteDTO: favoriteDTOList){
            FavoriteVO favoriteVO = ConvertUtil.convert(FavoriteVO.class, favoriteDTO);
            favoriteVO.setFavoriteTypeDesc(FavoriteTypeEnum.getDescByName(favoriteDTO.getFavoriteType()));
            if(Objects.equals(FavoriteTypeEnum.TRAVEL.name(), favoriteDTO.getFavoriteType())){
                TravelDTO travelDTO = travelService.getTravelById(favoriteDTO.getAssociateId());
                favoriteVO.setAssociateTitle(Objects.nonNull(travelDTO)? travelDTO.getName(): "已失效");
            }
            favoriteVOList.add(favoriteVO);
        }
        return favoriteVOList;
    }
}
