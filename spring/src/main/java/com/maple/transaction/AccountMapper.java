package com.maple.transaction;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/10/30 14:04
 * desc:
 */

@Repository
public class AccountMapper {

    @Resource
    private JdbcTemplate jdbcTemplate;


    public Account selectByName(String name){
        String sql = "select *from account where name = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), name);
    }

    public int updateById(Account account){
        String sql = "update account set balance = ? where id = ?";
        return jdbcTemplate.update(sql, account.getBalance(), account.getId());
    }


}
