package com.zjcds.czt.conf;

import com.zjcds.common.jpa.impl.CustomRepostoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories(basePackages={"com.zjcds.czt.dao.jpa","com.zjcds.common.syslog.dao.jpa"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        repositoryBaseClass= CustomRepostoryImpl.class)
public class JpaConfiguration {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter
                = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setPackagesToScan("com.zjcds.czt.domain.entity","com.zjcds.common.syslog.domain.entity");
        em.setPersistenceUnitName("jpa");
        em.setDataSource(dataSource);
        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.putAll(jpaProperties.getHibernateProperties(dataSource));
        em.setJpaPropertyMap(vendorProperties);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
