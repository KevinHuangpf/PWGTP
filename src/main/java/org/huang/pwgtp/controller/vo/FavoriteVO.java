package org.huang.pwgtp.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class FavoriteVO {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 收藏类型
     */
    private String favoriteTypeDesc;


    /**
     * 关联id
     */
    private Long associateId;


    /**
     * 关联收藏标题
     */
    private String associateTitle;

}
