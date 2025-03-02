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
     * 描述
     */
    private String description;

    /**
     * 手机号码
     */
    private String mobilePhone;

}
