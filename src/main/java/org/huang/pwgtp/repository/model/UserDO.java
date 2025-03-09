package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO extends BaseDO {



    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 密码
     */
    private String password;

    /**
     * 描述
     */
    private String description;

}
