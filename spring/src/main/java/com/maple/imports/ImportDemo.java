package com.maple.imports;

import com.maple.imports.annotation.MapleImport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 杨锋
 * @date 2022/11/10 13:56
 * desc:
 */

/* 直接使用import导入普通的类，创建的名字是类的全限定类名*/
//@Import(Student.class)
// spring会去循环扫描每一个配置类的所有注解，递归的看是否存在Import注入
@MapleImport
@ComponentScan("com.maple.imports")
public class ImportDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportDemo.class);

        Object student = applicationContext.getBean("com.maple.imports.entity.Student");
        System.out.println(student);
    }
}
