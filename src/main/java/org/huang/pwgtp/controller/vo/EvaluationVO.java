package org.huang.pwgtp.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationVO {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人
     */
    private UserVO creator;

    /**
     * 关联travelId
     */
    private Long travelId;

    /**
     * 评价内容
     */
    private String evaluationContent;



}
