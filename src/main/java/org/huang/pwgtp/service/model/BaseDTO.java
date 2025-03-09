package org.huang.pwgtp.service.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6936764157663074483L;

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
