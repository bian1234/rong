package com.byk.rong.common.config;

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
 * 写操作 数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.system.mapper.write", sqlSessionTemplateRef  = "systemSqlSessionTemplate")
public class SystemWriteDataSourceConfiguration {

    @Value("${spring.datasource.systemWrite.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.systemWrite.url}")
    private String url;

    @Value("${spring.datasource.systemWrite.username}")
    private String username;

    @Value("${spring.datasource.systemWrite.password}")
    private String password;

    @Bean(name = "systemDataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "systemSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("systemDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/rong/system/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "systemTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("systemDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "systemSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("systemSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}