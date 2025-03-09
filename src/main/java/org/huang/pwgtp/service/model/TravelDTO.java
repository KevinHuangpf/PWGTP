package org.huang.pwgtp.service.model;

import lombok.Data;
import org.huang.pwgtp.common.bizEnum.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class TravelDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3352740424181813082L;

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
     * {@link TravelStatusEnum}
     */
    private String status;

    /**
     * 行程类型
     * {@link TravelTypeEnum}
     */
    private String travelType;

    /**
     * 出行方式
     * 可多选
     * {@link TravelWayEnum}
     */
    private List<String> travelWayList;

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
