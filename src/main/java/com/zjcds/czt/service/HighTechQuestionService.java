package com.zjcds.czt.service;

import com.zjcds.common.base.domain.page.Paging;
import com.zjcds.common.jpa.PageResult;
import com.zjcds.czt.domain.entity.es.HighTechQuestion;
import org.springframework.stereotype.Service;


/**
 * created date：2018-12-24
 * @author niezhegang
 */
@Service
public interface HighTechQuestionService {

    /**
     * 分页查询高新问答
     * @param primaryKey
     * @param paging
     * @return
     */
    public PageResult<HighTechQuestion> queryHighTechQuestion(String primaryKey, Paging paging);



}
