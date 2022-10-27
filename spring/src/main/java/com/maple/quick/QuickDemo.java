package com.maple.quick;

import com.maple.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/26 9:54
 * desc:
 */

public class QuickDemo {

    private static final Logger logger = LoggerFactory.getLogger(QuickDemo.class);

    public static void main(String[] args) {
        // 不同配置文件的使用同一个id，会覆盖(一个配置问价重复会报错)
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml", "spring01.xml");

        Student bean = applicationContext.getBean("userBean", Student.class);
        logger.info(bean.toString());

    }
}
