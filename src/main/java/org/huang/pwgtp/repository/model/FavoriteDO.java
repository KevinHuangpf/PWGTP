package org.huang.pwgtp.repository.model;

import lombok.Data;

@Data
public class FavoriteDO extends BaseDO{


    /**
     * 收藏类型
     * {@link org.huang.pwgtp.common.bizEnum.FavoriteTypeEnum}
     */
    private String favoriteType;

    /**
     * 关联id
     */
    private Long associateId;
}
