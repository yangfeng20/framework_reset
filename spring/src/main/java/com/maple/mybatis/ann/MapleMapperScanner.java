package com.maple.mybatis.ann;

import com.maple.mybatis.config.MybatisImportConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:46
 * desc:
 */

@Import(MybatisImportConfig.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapleMapperScanner {

    String value();
}
