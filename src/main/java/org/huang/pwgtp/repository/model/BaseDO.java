package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDO {

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
