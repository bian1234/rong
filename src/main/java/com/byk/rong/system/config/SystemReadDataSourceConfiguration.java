package com.byk.rong.system.config;

import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 读操作数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.system.mapper.read", sqlSessionTemplateRef  = "systemReadSqlSessionTemplate")
public class SystemReadDataSourceConfiguration {

    @Value("${spring.datasource.systemRead.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.systemRead.url}")
    private String url;

    @Value("${spring.datasource.systemRead.username}")
    private String username;

    @Value("${spring.datasource.systemRead.password}")
    private String password;


    @Bean(name = "systemReadDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "systemReadSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("systemReadDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/rong/system/read/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "systemReadTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("systemReadDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "systemReadSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("systemReadSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}