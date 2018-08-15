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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 读操作数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.system.mapper.read", sqlSessionTemplateRef  = "slaverSqlSessionTemplate")
public class SystemReadDataSourceConfiguration {

    @Value("${spring.datasource.systemRead.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.systemRead.url}")
    private String url;

    @Value("${spring.datasource.systemRead.username}")
    private String username;

    @Value("${spring.datasource.systemRead.password}")
    private String password;


    @Bean(name = "systemDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "systemSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("systemDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/rong/system/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "systemTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("systemDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "slaverSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("systemSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}