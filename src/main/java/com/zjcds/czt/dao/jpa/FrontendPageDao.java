package com.zjcds.czt.dao.jpa;

import com.zjcds.common.jpa.CustomRepostory;
import com.zjcds.czt.domain.entity.jpa.FrontendPage;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author luokp on 2019/3/14.
 */
public interface FrontendPageDao extends CustomRepostory<FrontendPage, String> {

    @EntityGraph(attributePaths = "seoConfig")
    @Query("select p from FrontendPage p")
    public List<FrontendPage> findAllForFetchSeoConfig();

    @EntityGraph(attributePaths = "seoConfig")
    @Query("select p from FrontendPage p where p.code = :code")
    public FrontendPage findOneForFetchSeoConfig(@Param("code") String code);

}
