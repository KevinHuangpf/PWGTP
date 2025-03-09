package org.huang.pwgtp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.huang.pwgtp.repository.model.FavoriteDO;
import org.huang.pwgtp.repository.model.TravelDO;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    int insert(FavoriteDO favoriteDO);

    FavoriteDO getById(Long id);

    List<FavoriteDO> list(Long creatorUid, String favoriteType, Long associateId);

    int deleteById(Long id);



}
