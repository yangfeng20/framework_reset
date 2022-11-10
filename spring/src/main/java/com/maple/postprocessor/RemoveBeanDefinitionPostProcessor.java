package com.maple.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author 杨锋
 * @date 2022/11/10 10:45
 * desc:
 */

@Component
public class RemoveBeanDefinitionPostProcessor implements BeanFactoryPostProcessor {

    private static final String MATCHES_ENV_KEY = "consumer";

    private static final List<String> EXPECT_ENV_LIST = Arrays.asList("dev", "local");

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;

        // todo 可以不需要实体类的BeanDefinition对象，直接扫描主类的排除注解
        try {
            BeanDefinition entityExclude = beanFactory.getBeanDefinition("entityExclude");
            if (!EXPECT_ENV_LIST.contains(System.getProperty(MATCHES_ENV_KEY))) {
                return;
            }


            Class<?> entityExcludeClass = Thread.currentThread().getContextClassLoader().loadClass(entityExclude.getBeanClassName());
            if (entityExcludeClass.isAnnotationPresent(ExcludeComponents.class)) {
                ExcludeComponents annotation = entityExcludeClass.getAnnotation(ExcludeComponents.class);
                Class<?>[] excludeClassArray = annotation.value();
                for (Class<?> excludeClass : excludeClassArray) {
                    // 获取排除classBeanName
                    String excludeClassName = String.valueOf(new char[]{(char) (excludeClass.getSimpleName().charAt(0) + 32)}) + excludeClass.getSimpleName().substring(1);
                    // 删除BeanDefinition
                    beanFactory.removeBeanDefinition(excludeClassName);
                }
            }
        } catch (ClassNotFoundException | BeansException e) {
            System.out.println(e.getMessage());
        }

    }
}
