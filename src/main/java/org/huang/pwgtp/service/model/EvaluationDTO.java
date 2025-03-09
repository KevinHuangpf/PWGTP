package org.huang.pwgtp.service.model;

import lombok.Data;

import java.io.Serial;

@Data
public class EvaluationDTO extends BaseDTO{

    @Serial
    private static final long serialVersionUID = 7700744314535852569L;

    /**
     * 行程id
     */
    private Long travelId;


    /**
     *  评价内容
     */
    private String evaluationContent;
}
