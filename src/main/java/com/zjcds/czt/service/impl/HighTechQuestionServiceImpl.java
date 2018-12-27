package com.zjcds.czt.service.impl;

import com.zjcds.common.base.domain.page.Paging;
import com.zjcds.common.jpa.PageResult;
import com.zjcds.common.jpa.utils.PageUtils;
import com.zjcds.czt.dao.es.HighTechQuestionRepostory;
import com.zjcds.czt.domain.entity.es.HighTechQuestion;
import com.zjcds.czt.service.HighTechQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * created date：2018-12-24
 * @author niezhegang
 */
@Service
public class HighTechQuestionServiceImpl implements HighTechQuestionService {

    @Autowired
    private HighTechQuestionRepostory highTechQuestionRepostory;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public PageResult<HighTechQuestion> queryHighTechQuestion(String primaryKey, Paging paging) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if(StringUtils.isNotBlank(primaryKey)) {
            nativeSearchQueryBuilder.withQuery(matchQuery("question",primaryKey));
        }
        //排序和分页
        SearchQuery searchQuery = nativeSearchQueryBuilder
                .withIndices("high_tech_questions")
                .withTypes("table")
                .withPageable(PageUtils.transform(paging))
                .withHighlightFields(new HighlightBuilder.Field("question").fragmentSize(200))
                .build();
        Page<HighTechQuestion> highTechQuestionPage = elasticsearchTemplate.queryForPage(searchQuery, HighTechQuestion.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<HighTechQuestion> chunk = new ArrayList<HighTechQuestion>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    HighTechQuestion highTechQuestion = new HighTechQuestion();
                    highTechQuestion.setId(searchHit.getId());
                    if(searchHit.getHighlightFields().get("question") != null) {
                        highTechQuestion.setQuestion(searchHit.getHighlightFields().get("question").fragments()[0].string());
                    }
                    else {
                        highTechQuestion.setQuestion((String)searchHit.getSource().get("question"));
                    }
                    highTechQuestion.setAnswer((String)searchHit.getSource().get("answer"));
                    highTechQuestion.setAddtime(new Date((Long)searchHit.getSource().get("addtime")));
                    highTechQuestion.setUpdatedTime((new Date((Long)searchHit.getSource().get("updatedTime"))));
                    chunk.add(highTechQuestion);
                }
                return new AggregatedPageImpl(chunk,pageable,response.getHits().totalHits());
            }
        });

        //Page<HighTechQuestion> highTechQuestionPage = highTechQuestionRepostory.search(searchQuery);
        return PageUtils.transformPageResult(highTechQuestionPage,paging);
    }

}
