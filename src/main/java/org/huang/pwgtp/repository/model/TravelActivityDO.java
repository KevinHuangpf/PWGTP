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
    private Integer id;

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
    private Integer creatorUid;

    /**
     * 修改人
     */
    private Integer modifierUid;

    /**
     * 行程名称
     */
    private String name;

    /**
     * 行程描述
     */
    private String describe;

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
