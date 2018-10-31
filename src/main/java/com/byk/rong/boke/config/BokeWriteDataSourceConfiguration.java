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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 写操作 数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.boke.mapper.write", sqlSessionTemplateRef  = "bokeWriteSqlSessionTemplate")
public class BokeWriteDataSourceConfiguration {

    @Value("${spring.datasource.bokeWrite.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.bokeWrite.url}")
    private String url;

    @Value("${spring.datasource.bokeWrite.username}")
    private String username;

    @Value("${spring.datasource.bokeWrite.password}")
    private String password;

    @Bean(name = "bokeWriteDataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "bokeWriteSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("bokeWriteDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/rong/boke/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "bokeWriteTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("bokeWriteDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "bokeWriteSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("bokeWriteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}