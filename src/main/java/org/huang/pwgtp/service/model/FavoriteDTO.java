package org.huang.pwgtp.service.model;

import lombok.Data;

import java.io.Serial;

@Data
public class FavoriteDTO extends BaseDTO{

    @Serial
    private static final long serialVersionUID = -6548801958523317232L;
    /**
     * 收藏类型
     * {@link org.huang.pwgtp.common.bizEnum.FavoriteTypeEnum}
     */
    private String favoriteType;

    /**
     * 关联用户id
     */
    private Long UserId;

    /**
     * 关联id
     */
    private Long associateId;
}
