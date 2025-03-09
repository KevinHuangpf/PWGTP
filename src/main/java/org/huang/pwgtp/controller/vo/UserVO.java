package org.huang.pwgtp.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

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
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 描述
     */
    private String description;

}
