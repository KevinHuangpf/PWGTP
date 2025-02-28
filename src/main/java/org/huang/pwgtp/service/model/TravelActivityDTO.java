package org.huang.pwgtp.service.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TravelActivityDTO {

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

    /**
     * 行程名称
     */
    private String name;

    /**
     * 行程描述
     */
    private String description;

    /**
     * 计划招募成员人数
     */
    private Integer planRecruitMemberNumber;

    /**
     * 已经招募成员
     */
    private List<Long> hasRecruitedMemberList;

}
