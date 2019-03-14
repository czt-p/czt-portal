package com.zjcds.czt.dao.jpa;

import com.zjcds.common.jpa.CustomRepostory;
import com.zjcds.czt.domain.entity.jpa.RegisterDepartment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public interface RegisterDepartmentDao extends CustomRepostory<RegisterDepartment, Integer> {

    @Query("select rd from RegisterDepartment rd where rd.name = :name and rd.name is not null")
    public List<RegisterDepartment> findByName(@Param("name") String name);

}
