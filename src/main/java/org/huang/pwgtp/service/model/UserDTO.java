package org.huang.pwgtp.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Date;

@Data
public class UserDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 6184250008293686331L;

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
