package com.czkj.multi.druid.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author steven.sheng
 * @Date 2020/3/17/01710:17
 */
@Configuration
@Slf4j
@MapperScan(basePackages = "com.czkj.multi.druid.dao.one", sqlSessionFactoryRef = "sqlSessionFactory1"/*,
        sqlSessionTemplateRef = "sqlSessionTemplate1"*/)
public class MyBatisConfigOne {
    // 配置主数据源
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource dsOne() throws SQLException {
        log.info("开始配置数据源信息--------");
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory SqlSessionFactory1(@Qualifier("dsOne") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.czkj.druid.entity");
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mappers/one/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

   /* @Primary
    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate SqlSessionTemplate1(
            @Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }*/

    @Bean("txManager1")
    public PlatformTransactionManager transactionManager(@Qualifier("dsOne") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    private static final int TX_METHOD_TIMEOUT = 50000;

    // 事务的实现Advice
    @Bean
    public TransactionInterceptor txAdvice1(@Qualifier("txManager1")PlatformTransactionManager m) {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        source.setNameMap(txMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(m, source);
        return txAdvice;
    }
}
