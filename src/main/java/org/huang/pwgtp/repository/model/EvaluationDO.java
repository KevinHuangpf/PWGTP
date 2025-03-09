package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationDO extends BaseDO{

    /**
     * 关联id
     */
    private Long travelId;

    /**
     * 评价内容
     */
    private String evaluationContent;

}
