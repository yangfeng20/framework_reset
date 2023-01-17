package com.maple.mybatis.source;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
//@MapperScan("com.maple.mybatis.mapper")
public class MybatisSpringSourceConfig {

    //@Bean
    //public DataSource dataSource() {
    //    return new EmbeddedDatabaseBuilder().addScript("schema.sql").build();
    //}
    //
    //@Bean
    //public DataSourceTransactionManager transactionManager() {
    //    return new DataSourceTransactionManager(dataSource());
    //}
    //
    //@Bean
    //public SqlSessionFactory sqlSessionFactory() throws Exception {
    //    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    //    sessionFactory.setDataSource(dataSource());
    //    return sessionFactory.getObject();
    //}


    @Bean
    public SqlSessionFactory sqlSessionFactory(){
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}