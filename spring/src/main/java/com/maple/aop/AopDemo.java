package com.maple.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author 杨锋
 * @date 2022/10/29 21:03
 * desc:
 */

public class AopDemo {
    public static void main(String[] args) {

        // 保存cglib动态生成的代码
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\Program Dev\\Java\\05-Dev\\framework_reset\\spring\\src\\main\\java\\com\\maple\\aop\\generate");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAopConfig.class);

        //UserService userService = applicationContext.getBean("userService", UserService.class);
        //userService.createUser(new UserParam());

        UserInterface userApi = applicationContext.getBean("userApi", UserInterface.class);
        userApi.test02(1, 2, new int[]{1});
        userApi.test01(1);

        ArrayList<Object> objects = new ArrayList<>();

        System.out.println(Collection.class.isAssignableFrom(objects.getClass()));

    }
}
