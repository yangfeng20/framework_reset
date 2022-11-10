package com.maple.imports;

import com.maple.imports.entity.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 杨锋
 * @date 2022/11/10 14:00
 * desc:
 */
@Import(Student.class)
@Configuration
public class RegistryConfig {

    //@Bean
    //public Student student(){
    //    return new Student();
    //}
}
