package com.maple.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 杨锋
 * @date 2022/10/29 21:17
 * desc:
 */

@Configuration
@ComponentScan("com.maple.aop")
@EnableAspectJAutoProxy
public class SpringAopConfig {


}
