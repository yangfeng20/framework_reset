package com.maple.mybatis.ibatis.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/13 21:22
 * desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlSessionFactory {


    /**
     * 事务管理器属性
     */
    private Transaction transaction;



    /**
     * sqlId,sql标签信息
     */
    private Map<String, MappedStatement> MappedStatements;
}
