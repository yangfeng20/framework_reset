package com.maple.mybatis.ibatis.core;

/**
 * @author 杨锋
 * @date 2022/10/13 21:28
 * desc:
 */

public class MappedStatement {


    private String sqlId;
    private String resultType;
    private String sql;
    private String parameterType;
    private String sqlType;

    public MappedStatement(String sqlId, String resultType, String sql, String parameterType, String sqlType) {
        this.sql = sql;
        this.sqlId = sqlId;
        this.sqlType = sqlType;
        this.resultType = resultType;
        this.parameterType = parameterType;
    }
}
