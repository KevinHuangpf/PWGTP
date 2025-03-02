package org.huang.pwgtp.repository.model;

import lombok.Data;
import org.huang.pwgtp.common.bizEnum.TravelWayEnum;

import java.util.Date;
import java.util.List;

/**
 * 行程活动
 */
@Data
public class TravelActivityDO extends BaseDO{


    /**
     * 行程名称
     */
    private String name;

    /**
     * 行程描述
     */
    private String description;


    /**
     * 行程状态
     * {@link org.huang.pwgtp.common.bizEnum.TravelActivityStatusEnum}
     */
    private String status;

    /**
     * 行程类型
     */
    private String travelType;

    /**
     * 出行方式
     * 可多选
     * {@link TravelWayEnum}
     * List<Long> jsonString
     */
    private String travelWay;

    /**
     * 行程开始时间
     */
    private Date travelStartTime;

    /**
     * 行程结束时间
     */
    private Date travelEndTime;


    /**
     * 计划招募成员人数
     */
    private Integer planRecruitMemberNumber;


    /**
     * 已经招募成员
     * List<Long> jsonString
     */
    private String hasRecruitedMemberList;

    /**
     * 行程计划/攻略
     */
    private String travelPlan;

    /**
     * 删除状态
     * 1=删除，0未删除，默认0
     */
    private Integer deleted;

}
