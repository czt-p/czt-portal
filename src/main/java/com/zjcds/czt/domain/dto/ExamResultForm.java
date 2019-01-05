package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luokp on 2019/1/5.
 */
public class ExamResultForm {

    @Getter
    @Setter
    public static class BasicExamResult extends BaseBean {
        private boolean passed;

        public BasicExamResult() {
        }

        public BasicExamResult(boolean passed) {
            this.passed = passed;
        }
    }

    @Getter
    @Setter
    public static class HighExamResult extends BaseBean {
        private Double score;
    }

    @Getter
    @Setter
    public static class SendDetailResult extends BaseBean {
        private String companyName;
        private String telephone;
    }

    public static class ExamResult extends BaseBean {

    }

}
