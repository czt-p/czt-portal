package com.zjcds.czt.dao.es;

import com.zjcds.czt.domain.entity.es.HighTechQuestion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * created date：2018-12-24
 * @author niezhegang
 */
public interface HighTechQuestionRepostory extends ElasticsearchRepository<HighTechQuestion,String>{

}
