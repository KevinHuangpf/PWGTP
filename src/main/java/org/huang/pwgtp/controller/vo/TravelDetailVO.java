package org.huang.pwgtp.controller.vo;


import lombok.Data;
import org.huang.pwgtp.common.bizEnum.TravelWayEnum;

import java.util.Date;
import java.util.List;

@Data
public class TravelDetailVO {

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
    private UserVO creator;


    /**
     * 行程名称
     */
    private String name;

    /**
     * 行程描述
     */
    private String description;


    /**
     * 行程状态描述
     */
    private String statusDesc;

    /**
     * 行程类型描述
     */
    private String travelTypeDesc;

    /**
     * 出行方式描述
     * 可多选
     */
    private List<String> travelWayDescList;

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
    private List<UserVO> hasRecruitedMemberList;

    /**
     * 已经招募成员数量
     */
    private Integer hasRecruitedMemberNumber;

    /**
     * 行程计划/攻略
     */
    private String travelPlan;

    /**
     * 评价列表
     */
    private List<EvaluationVO> evaluationVOList;


}
