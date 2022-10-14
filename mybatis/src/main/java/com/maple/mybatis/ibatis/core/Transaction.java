package com.maple.mybatis.ibatis.core;

import java.sql.Connection;

/**
 * @author 杨锋
 * @date 2022/10/13 21:31
 * desc:
 */

public interface Transaction {


    /**
     * 获得连接
     * 获取数据来源
     *
     * @return {@link Connection}
     */
    Connection getConnection();

    /**
     * 提交
     */
    void commit();

    /**
     * 回滚
     */
    void rollback();

    /**
     * 关闭
     */
    void close();

    /**
     * 打开连接
     */
    void openConnection();
}
