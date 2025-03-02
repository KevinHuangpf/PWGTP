package org.huang.pwgtp.service.model;

import lombok.Data;
import org.huang.pwgtp.common.bizEnum.TravelWayEnum;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class TravelActivityDTO extends BaseDTO {

    /**
     * 行程名称
     */
    private String name;

    /**
     * 行程描述
     */
    private String description;


    /**
     * 行程类型
     */
    private String travelType;


    /**
     * 出行方式
     * 可多选
     * {@link TravelWayEnum}
     */
    private List<String> travelWay;

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
     */
    private Set<Long> hasRecruitedMemberList;

    /**
     * 行程计划/攻略
     */
    private String travelPlan;

}
