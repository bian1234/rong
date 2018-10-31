//package com.byk.rong.common.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//
///**
// */
//@Configuration
//@MapperScan(basePackages = "com.prostate.common.dao", sqlSessionTemplateRef  = "commonSqlSessionTemplate")
//public class CommonDataSourceConfiguration {
//
//    @Value("${spring.datasource.common.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.common.url}")
//    private String url;
//
//    @Value("${spring.datasource.common.username}")
//    private String username;
//
//    @Value("${spring.datasource.common.password}")
//    private String password;
//
//
//    @Bean(name = "commonDataSource")
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(this.driverClassName);
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.username);
//        dataSource.setPassword(this.password);
//        return dataSource;
//    }
//
//    @Bean(name = "commonSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("commonDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/common/*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean(name = "commonTransactionManager")
//    public DataSourceTransactionManager transactionManager(@Qualifier("commonDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "commonSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("commonSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}