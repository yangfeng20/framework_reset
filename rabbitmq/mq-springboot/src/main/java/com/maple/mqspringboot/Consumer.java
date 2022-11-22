package com.maple.mqspringboot;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 服务
 *
 * @author yangfeng
 * @date 2022/11/21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Consumer {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}