package com.byk.rong.boke.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 读操作数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.boke.mapper.read", sqlSessionTemplateRef  = "bokeReadSqlSessionTemplate")
public class BokeReadDataSourceConfiguration {

    @Value("${spring.datasource.bokeRead.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.bokeRead.url}")
    private String url;

    @Value("${spring.datasource.bokeRead.username}")
    private String username;

    @Value("${spring.datasource.bokeRead.password}")
    private String password;


    @Bean(name = "bokeReadDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "bokeReadSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("bokeReadDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/rong/boke/read/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "bokeReadTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("bokeReadDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "bokeReadSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("bokeReadSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}