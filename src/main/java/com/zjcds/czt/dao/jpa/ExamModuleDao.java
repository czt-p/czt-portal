package com.zjcds.czt.dao.jpa;

import com.zjcds.common.jpa.CustomRepostory;
import com.zjcds.czt.domain.entity.jpa.ExamModule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public interface ExamModuleDao extends CustomRepostory<ExamModule, Integer> {

    @EntityGraph(attributePaths = "questions")
    @Query("select em from ExamModule em where em.id = :id")
    public ExamModule findOneForFetchQuestions(@Param("id") Integer id);

    @EntityGraph(attributePaths = "questions")
    @Query("select distinct em from ExamModule em")
    public List<ExamModule> findAllForFetchQuestions();

}
