package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationDO extends BaseDO{

    /**
     * 评价类型
     */
    private String type;

    /**
     * 关联id
     */
    private Long travelActivityId;

    /**
     * 评价内容
     */
    private String evaluationContent;

}
