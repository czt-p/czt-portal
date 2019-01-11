package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public class ExamResultForm {

    @Getter
    @Setter
    public static class BasicExamResult extends BaseBean {
        @ApiModelProperty(value = "是否通过", required = true, readOnly = true)
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
        @ApiModelProperty(value = "分数", required = true, readOnly = true)
        private Double score;

        public HighExamResult() {
        }

        public HighExamResult(Double score) {
            this.score = score;
        }
    }

    @Getter
    @Setter
    public static class SendDetailResult extends BaseBean {
        @ApiModelProperty(value = "公司名称", required = true, readOnly = true)
        private String companyName;
        @ApiModelProperty(value = "联系电话", required = true, readOnly = true)
        private String telephone;
    }

    @Getter
    @Setter
    public static class ExamResult extends BaseBean {
        @ApiModelProperty(value = "分数", required = true, readOnly = true)
        private Double score;
        @ApiModelProperty(value = "知识产权情况", required = true, readOnly = true)
        private String ipSituation;
        @ApiModelProperty(value = "科技成果转化情况", required = true, readOnly = true)
        private String saSituation;
        @ApiModelProperty(value = "企业研发组织管理水平", required = true, readOnly = true)
        private String ramSituation;
        @ApiModelProperty(value = "是否通过", required = true, readOnly = true)
        private String fgSituation;
        @ApiModelProperty(value = "财务增长情况", required = true, readOnly = true)
        private List<ExamQuestionForm.Answer> answers;
    }

}
