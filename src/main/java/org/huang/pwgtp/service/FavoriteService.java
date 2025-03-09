package org.huang.pwgtp.service;

import com.github.pagehelper.PageHelper;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.bizEnum.TravelStatusEnum;
import org.huang.pwgtp.repository.FavoriteMapper;
import org.huang.pwgtp.repository.model.FavoriteDO;
import org.huang.pwgtp.repository.model.TravelDO;
import org.huang.pwgtp.service.model.FavoriteDTO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;


    public void addFavorite(Long creatorUid, String favoriteType, Long associateId) throws Exception{
        List<FavoriteDO> favoriteDOList = favoriteMapper.list(creatorUid, favoriteType, associateId);
        if(!CollectionUtils.isEmpty(favoriteDOList)){
            throw new Exception("已收藏");
        }
        FavoriteDO favoriteDO = new FavoriteDO();
        favoriteDO.setCreatorUid(creatorUid);
        favoriteDO.setModifierUid(creatorUid);
        favoriteDO.setFavoriteType(favoriteType);
        favoriteDO.setAssociateId(associateId);
        int updateResult = favoriteMapper.insert(favoriteDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public void deleteFavorite(Long id) throws Exception{
        FavoriteDO favoriteDO = favoriteMapper.getById(id);
        if(Objects.isNull(favoriteDO)){
            throw new Exception("记录不存在，无法操作");
        }
        int updateResult = favoriteMapper.deleteById(id);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public CommonPage<FavoriteDTO> listFavoriteByPage(Integer pageNum, Integer pageSize, Long userId, String favoriteType){
        PageHelper.startPage(pageNum, pageSize).setOrderBy("gmt_create desc");
        List<FavoriteDO> favoriteDOList = favoriteMapper.list(userId, favoriteType, null);
        CommonPage<FavoriteDO> commonPageDO = CommonPage.restPage(favoriteDOList);
        CommonPage<FavoriteDTO> commonPageDTO = new CommonPage<>();
        BeanUtils.copyProperties(commonPageDO, commonPageDTO);
        commonPageDTO.setList(ConvertUtil.convertList(FavoriteDTO.class, commonPageDO.getList()));
        return commonPageDTO;
    }
}
