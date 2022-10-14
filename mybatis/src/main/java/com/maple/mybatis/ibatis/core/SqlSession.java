package com.maple.mybatis.ibatis.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/14 9:58
 * desc:
 */

public class SqlSession {

    private SqlSessionFactory sqlSessionFactory;

    public SqlSession(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void commit() {

        sqlSessionFactory.getTransaction().commit();

    }

    public void rollback() {

        sqlSessionFactory.getTransaction().rollback();

    }

    public void close() {

        sqlSessionFactory.getTransaction().close();

    }


    public int insert(String sqlId, Object obj) {
        MappedStatement mappedStatement = sqlSessionFactory.getMappedStatements().get(sqlId);
        Connection connection = sqlSessionFactory.getTransaction().getConnection();
        // 获取sql语句
        // insert into t_car(id,car_num,brand,guide_price,produce_time,car_type) values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})
        String batisSql = mappedStatement.getSql();
        // insert into t_car(id,car_num,brand,guide_price,produce_time,car_type) values(null,?,?,?,?,?)
        String sql = batisSql.replaceAll("#\\{[a-zA-Z0-9_\\$]*}", "?");

        // 重点一步
        Map<Integer, String> map = new HashMap<>();
        int index = 1;
        while (batisSql.indexOf("#") >= 0) {
            int beginIndex = batisSql.indexOf("#") + 2;
            int endIndex = batisSql.indexOf("}");
            map.put(index++, batisSql.substring(beginIndex, endIndex).trim());
            batisSql = batisSql.substring(endIndex + 1);
        }

        final PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);

            // 给?赋值
            map.forEach((k, v) -> {
                try {
                    // 获取java实体类的get方法名
                    String getMethodName = "get" + v.toUpperCase().charAt(0) + v.substring(1);
                    Method getMethod = obj.getClass().getDeclaredMethod(getMethodName);
                    Object result = getMethod.invoke(obj);
                    if (result == null) {
                        ps.setObject(k, null);
                    }
                    if (result instanceof String) {
                        ps.setString(k, result.toString());
                    } else if (result instanceof Long) {
                        ps.setLong(k, (Long) result);
                    } else if (result instanceof Integer) {
                        ps.setInt(k, (Integer) result);
                    } else if (result instanceof Boolean) {
                        ps.setBoolean(k, (Boolean) result);
                    } else if (result instanceof Date) {
                        ps.setDate(k, new java.sql.Date(((Date) result).getTime()));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            int count = ps.executeUpdate();
            ps.close();
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object selectOne(String sqlId, Object parameterObj) {
        MappedStatement mappedStatement = sqlSessionFactory.getMappedStatements().get(sqlId);
        Connection connection = sqlSessionFactory.getTransaction().getConnection();
        // 获取sql语句
        String godbatisSql = mappedStatement.getSql();
        String sql = godbatisSql.replaceAll("#\\{[a-zA-Z0-9_\\$]*}", "?");
        // 执行sql
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object obj = null;
        try {
            ps = connection.prepareStatement(sql);
            if (parameterObj != null) {
                ps.setString(1, parameterObj.toString());
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                // 将结果集封装对象，通过反射
                String resultType = mappedStatement.getResultType();
                Class<?> aClass = Class.forName(resultType);
                Constructor<?> con = aClass.getDeclaredConstructor();
                obj = con.newInstance();
                // 给对象obj属性赋值
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String setMethodName = "set" + columnName.toUpperCase().charAt(0) + columnName.substring(1);
                    Method setMethod = aClass.getDeclaredMethod(setMethodName, aClass.getDeclaredField(columnName).getType());
                    setMethodInvoke(obj, setMethod, rs, columnName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    private void setMethodInvoke(Object obj, Method setMethod, ResultSet rs, String columnName) throws Exception {
        try {
            setMethod.invoke(obj, rs.getString(columnName));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            try {
                setMethod.invoke(obj, rs.getLong(columnName));
            } catch (IllegalArgumentException e1) {
                e1.printStackTrace();
                try {
                    setMethod.invoke(obj, rs.getDate(columnName));
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    try {
                        setMethod.invoke(obj, rs.getLong(columnName));
                    } catch (IllegalArgumentException e3) {
                        e3.printStackTrace();
                        try {
                            setMethod.invoke(obj, rs.getBoolean(columnName));
                        } catch (IllegalArgumentException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
