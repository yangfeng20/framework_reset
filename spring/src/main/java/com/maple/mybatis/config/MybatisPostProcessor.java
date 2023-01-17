package com.maple.mybatis.config;

import com.maple.mybatis.mapper.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:15
 * desc:
 */

// 弃用，使用MybatisImportConfig方式注册
@Deprecated
//@Component
public class MybatisPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        // 构建通用beanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        // 设置beanClass
        beanDefinition.setBeanClass(MybatisFactoryBean.class);
        // 指定构造器
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
        // 注册
        beanDefinitionRegistry.registerBeanDefinition("userMapper", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
