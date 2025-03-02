package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO {

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
     * 描述
     */
    private String description;


}
