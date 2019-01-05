package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public class ExamQuestionForm {

    @Getter
    @Setter
    public static class Owner extends BaseBean {
        private Integer id;
        private String content;
        private Integer type;
        private Integer orderNumber;
    }

    @Getter
    @Setter
    public static class OwnerWithAnswer extends Owner {
        private Object answer;
    }

    @Getter
    @Setter
    public static class ChoiceQuestion extends Owner {
        private List<OptionForm.OwnerWithoutScore> options;
    }

    @Getter
    @Setter
    public static class ChoiceQuestionWithAnswer extends OwnerWithAnswer {
        private List<OptionForm.OwnerWithoutScore> options;
    }

    @Getter
    @Setter
    public static class Answer extends BaseBean {
        private Integer id;

        private Integer answer;
    }

}
