package com.maple.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.List;

/**
 * @author 杨锋
 * @date 2022/11/10 9:36
 * desc:
 */


public class CustomCondition implements Condition {

    private static final String MATCHES_ENV_KEY = "consumer";

    private static final List<String> EXPECT_ENV_LIST = Arrays.asList("dev", "local");

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !EXPECT_ENV_LIST.contains(context.getEnvironment().getProperty(MATCHES_ENV_KEY));
    }
}
