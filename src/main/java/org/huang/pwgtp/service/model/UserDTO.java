package org.huang.pwgtp.service.model;

import lombok.Data;

@Data
public class UserDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

}
