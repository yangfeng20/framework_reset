package com.maple.instance;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 杨锋
 * @date 2022/10/29 10:23
 * desc:
 */

public class FactoryBeanPerosn implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
