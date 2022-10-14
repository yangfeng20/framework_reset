package com.maple.mybatis.ibatis.core;

import com.maple.mybatis.ibatis.utils.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/13 21:23
 * desc:
 */

public class SqlSessionFactoryBuild {


    public SqlSessionFactory build(InputStream inputStream) {


        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element envs = (Element) document.selectSingleNode("/configuration/environments");
        String defaultEnvId = envs.attributeValue("default");

        Element env = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultEnvId + "']");
        Element transactionElement = env.element("transactionManager");
        Element dataSourceElement = env.element("dataSource");
        Element mappers = (Element) document.selectSingleNode("/configuration/mappers");

        DataSource dataSource = getDataSource(dataSourceElement);


        Transaction transaction = getTransaction(transactionElement, dataSource);
        Map<String, MappedStatement> mappedStatementMap = getMappedStatements(mappers);
        return new SqlSessionFactory(transaction, mappedStatementMap);
    }


    private Map<String, MappedStatement> getMappedStatements(Element mappers) {
        Map<String, MappedStatement> mappedStatements = new HashMap<>();
        mappers.elements().forEach(mapperElt -> {
            try {
                String resource = mapperElt.attributeValue("resource");
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(Resources.getResourceAsStream(resource));
                Element mapper = (Element) document.selectSingleNode("/mapper");
                String namespace = mapper.attributeValue("namespace");

                mapper.elements().forEach(sqlMapper -> {
                    String sqlId = sqlMapper.attributeValue("id");
                    String sql = sqlMapper.getTextTrim();
                    String parameterType = sqlMapper.attributeValue("parameterType");
                    String resultType = sqlMapper.attributeValue("resultType");
                    String sqlType = sqlMapper.getName().toLowerCase();
                    // 封装GodMappedStatement对象
                    MappedStatement godMappedStatement = new MappedStatement(sqlId, resultType, sql, parameterType, sqlType);
                    mappedStatements.put(namespace + "." + sqlId, godMappedStatement);
                });

            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });
        return mappedStatements;
    }


    private Transaction getTransaction(Element transactionManagerElt, DataSource dataSource) {
        String type = transactionManagerElt.attributeValue("type").toUpperCase();
        Transaction transaction = null;
        if ("JDBC".equals(type)) {
            // 使用JDBC事务
            transaction = new JdbcTransaction(dataSource, false, null);
        } else if ("MANAGED".equals(type)) {
            // 事务管理器是交给JEE容器的
        }
        return transaction;
    }

    private DataSource getDataSource(Element dataSourceElt) {
        // 获取所有数据源的属性配置
        Map<String, String> dataSourceMap = new HashMap<>();
        dataSourceElt.elements("property").forEach(propertyElt -> {
            dataSourceMap.put(propertyElt.attributeValue("name"), propertyElt.attributeValue("value"));
        });

        String dataSourceType = dataSourceElt.attributeValue("type").toUpperCase();
        DataSource dataSource = null;
        if ("POOLED".equals(dataSourceType)) {
            dataSource = new PooledDataSource();
        } else if ("UNPOOLED".equals(dataSourceType)) {
            dataSource = new UnPooledDataSource(dataSourceMap.get("driver"), dataSourceMap.get("url"), dataSourceMap.get("username"), dataSourceMap.get("password"));
        } else if ("JNDI".equals(dataSourceType)) {

        }
        return dataSource;
    }

}
