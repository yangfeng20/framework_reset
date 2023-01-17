package com.maple.mybatis.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:12
 * desc:
 */


public class MybatisFactoryBean implements FactoryBean {

    private final Class<?> mapperInterface;

    /**
     * {@link com.maple.mybatis.config.MybatisImportConfig.SqlSessionConfiguration#sqlSessionFactory()}
     * @param sqlSessionFactory
     */
    @Autowired
    public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addMapper(mapperInterface);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    private  SqlSession sqlSession;


    public MybatisFactoryBean(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return sqlSession.getMapper(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
