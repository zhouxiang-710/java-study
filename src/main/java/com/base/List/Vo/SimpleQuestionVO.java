package com.base.List.Vo;

import lombok.Data;

@Data
public class SimpleQuestionVO {

    private Long questionConfigId;

    private String questionNumber;
    private String questionName;

    private Long markingConfigId;

    /**
     * 是否打分单元
     */
    private Integer isMinCell;
}
