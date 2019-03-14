package com.zjcds.czt.dao.jpa.impl;

import com.zjcds.czt.dao.jpa.SubsidyPolicyDao;
import com.zjcds.czt.dao.jpa.SubsidyPolicyDaoCustom;
import com.zjcds.czt.domain.entity.jpa.SubsidyPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author luokp on 2019/1/9.
 */
public class SubsidyPolicyDaoImpl implements SubsidyPolicyDaoCustom {

    @Autowired
    private SubsidyPolicyDao subsidyPolicyDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<SubsidyPolicy> findTop10Modify() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubsidyPolicy> query = criteriaBuilder.createQuery(SubsidyPolicy.class);
        Root<SubsidyPolicy> root = query.from(SubsidyPolicy.class);
        query.select(root);
        query.orderBy(criteriaBuilder.desc(root.get("modifyTime")));
        return entityManager.createQuery(query).setMaxResults(10).getResultList();
    }
}
