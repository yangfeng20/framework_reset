package com.maple.jdbc.transaction;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author yangfeng
 * @date : 2023/6/16 15:20
 * desc:
 */
@Component
public class Service01 {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Transactional(rollbackFor = Exception.class)
    public void buy(Long id) {

        Assert.isTrue(this.getStock(id) > 0, "库存不足，无法buy");

        int update = jdbcTemplate.update("update goods set stock = stock-1 where id = " + id);
        Assert.isTrue(update == 1, "库存不足，无法buy");
        System.out.println("购买成功");
    }


    public Integer getStock(Long id) {
        return jdbcTemplate.queryForObject("select stock from goods where id = " + id, Integer.class);
    }
}
