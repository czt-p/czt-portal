package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public class ExamModuleForm {

    @Getter
    @Setter
    public static class Owner extends BaseBean {
        private Integer id;

        private String name;
    }

    @Getter
    @Setter
    public static class OwnerWithQuestionAnswer extends Owner {
        private List<ExamQuestionForm.OwnerWithAnswer> questions;
    }

}
