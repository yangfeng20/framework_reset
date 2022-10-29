package com.maple.instance;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/29 10:32
 * desc: 用来创建Date的工厂bean
 */

public class DateFactoryBean implements FactoryBean<Date> {

    private String dateStr;

    public DateFactoryBean(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public Date getObject() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(dateStr);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
