package org.huang.pwgtp.service.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {

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
     * 创建人
     */
    private Long creatorUid;

    /**
     * 修改人
     */
    private Long modifierUid;


}
