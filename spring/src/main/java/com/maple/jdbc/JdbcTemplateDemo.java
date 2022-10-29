package com.maple.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/29 17:39
 * desc: 使用jdbc模版
 */

public class JdbcTemplateDemo {
    public static void main1(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        // 查询
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from student limit 10");
        queryForList.forEach(System.out::println);

        Student student = jdbcTemplate.queryForObject("select * from student where id = ?", new BeanPropertyRowMapper<>(Student.class), 1);
        System.out.println(student);

        // 插入
        //jdbcTemplate.update("insert into student (name, age) values (?,?)", "李四", 10);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDruidConfig.class);
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        // 查询
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from student limit 10");
        queryForList.forEach(System.out::println);
    }
}
