package com.maple.mybatis.config;

import com.maple.mybatis.ann.MapleMapperScanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:45
 * desc:
 */

@Slf4j
public class MybatisImportConfig implements ImportBeanDefinitionRegistrar {

    @Configuration
    static class SqlSessionConfiguration{

        /**
         * {@link MybatisFactoryBean#setSqlSession(SqlSessionFactory)}
         * @return
         */
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


    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata, @NonNull BeanDefinitionRegistry registry) {
        // 获取mapper扫描路径并扫描
        Map<String, Object> mapperScannerProperty = importingClassMetadata.getAnnotationAttributes(MapleMapperScanner.class.getName());
        if (mapperScannerProperty == null) {
            log.error("MapperScanner扫描路径为空");
            return;
        }
        MapperScanner scanner = new MapperScanner(registry);
        // 指定mapper扫描下都是mapper类，就不需要@Componnet
        scanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan((String) mapperScannerProperty.get("value"));
        if (beanDefinitionHolders.isEmpty()) {
            log.error("扫描路径没有Mapper");
            return;
        }

        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            ScannedGenericBeanDefinition mapperBeanDefinition = (ScannedGenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            // 指定构造器
            mapperBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(forClass(mapperBeanDefinition.getBeanClassName()));
            // 设置beanClass
            mapperBeanDefinition.setBeanClass(MybatisFactoryBean.class);
            // 注册
            log.error("beanDefinition: {}", mapperBeanDefinition);
            registry.registerBeanDefinition(beanDefinitionHolder.getBeanName(), mapperBeanDefinition);
        }
    }

    private Class<?> forClass(String className) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("加载类失败: " + className);
    }

    /**
     * 自定义mapper扫描
     */
    static class MapperScanner extends ClassPathBeanDefinitionScanner {

        public MapperScanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        protected Set<BeanDefinitionHolder> doScan(@NonNull String... basePackages) {
            return super.doScan(basePackages);
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            // 只扫描接口
            AnnotationMetadata metadata = beanDefinition.getMetadata();
            return metadata.isInterface();
        }


    }
}
