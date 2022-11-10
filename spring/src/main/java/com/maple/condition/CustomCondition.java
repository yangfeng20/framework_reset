package com.maple.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/11/10 9:36
 * desc:
 */


public class CustomCondition implements Condition {

    private static final String MATCHES_ENV_KEY = "consumer";

    private static final List<String> EXPECT_ENV_LIST = Arrays.asList("dev", "local");

    private static final String MATCHES_CLASS = "com.maple.condition.ExcludeComponents";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Map<String, Object> metadataAnnotationMap = metadata.getAnnotationAttributes(MATCHES_CLASS);
        // 不包含指定环境或者没有注解，不做处理
        if (!EXPECT_ENV_LIST.contains(context.getEnvironment().getProperty(MATCHES_ENV_KEY)) || metadataAnnotationMap == null) {
            return false;
        }

        Class<?>[] excludeClassArray = (Class<?>[]) metadataAnnotationMap.get("value");
        for (Class<?> excludeClass : excludeClassArray) {
            // 获取排除classBeanName
            String excludeClassName = String.valueOf(new char[]{(char) (excludeClass.getSimpleName().charAt(0) + 32)}) + excludeClass.getSimpleName().substring(1);
            // todo 排除beanDefinition 还没有创建处理，会报错，后面看看能不能用beanFactoryPostProcessor处理
            context.getRegistry().removeBeanDefinition(excludeClassName);
        }

        return false;
    }
}
