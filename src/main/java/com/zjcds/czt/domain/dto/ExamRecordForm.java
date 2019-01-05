package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public class ExamRecordForm {

    @Getter
    @Setter
    public static class Owner extends BaseBean {
        private Integer id;

        private String companyName;

        private String telephone;

        private Double score;

        private String result;

        private Date examTime;
    }

    @Getter
    @Setter
    public static class OwnerWithDetails extends Owner {
        private List<ExamModuleForm.OwnerWithQuestionAnswer> details;
    }

}
