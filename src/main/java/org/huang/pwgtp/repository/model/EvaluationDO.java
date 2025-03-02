package org.huang.pwgtp.repository.model;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationDO extends BaseDO{


    private String type;

    private String evaluationType;



    /**
     * 评价内容
     */
    private String evaluationContent;


}
