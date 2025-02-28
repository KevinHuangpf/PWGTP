package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 行程活动
 */
@Data
public class TravelActivityDO {

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
     * List<Long> jsonString
     */
    private String hasRecruitedMemberList;

}
