package org.huang.pwgtp.controller.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TravelActivitySaveVO {

    /**
     * 编辑时必传
     */
    private Integer id;

    /**
     * 创建人
     */
    private Long creatorUid;

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


}
