package org.huang.pwgtp.service.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends BaseDTO {

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;



}
