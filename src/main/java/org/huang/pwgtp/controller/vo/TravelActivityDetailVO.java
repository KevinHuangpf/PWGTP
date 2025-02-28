package org.huang.pwgtp.controller.vo;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TravelActivityDetailVO {

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
    private UserVO creatorUser;


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
    private List<UserVO> hasRecruitedMemberList;

    /**
     * 已经招募成员数量
     */
    private Integer hasRecruitedMemberNumber;


}
