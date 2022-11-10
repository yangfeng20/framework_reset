package com.maple.postprocessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 杨锋
 * @date 2022/11/10 9:55
 * desc:
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface ExcludeComponents {

    Class<?>[] value();

    //@AliasFor( value = "value")
    //Class<?>[] excludes() default {};
}
