package com.maple.mybatis.ibatis.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 杨锋
 * @date 2022/10/13 21:41
 * desc:
 */

@NoArgsConstructor
@AllArgsConstructor
public class JdbcTransaction implements Transaction {

    private DataSource dataSource;

    private boolean autoCommit;

    private Connection curConnection;

    @Override
    public Connection getConnection() {
        return curConnection;
    }

    @Override
    public void commit() {

        try {
            curConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void rollback() {

        try {
            curConnection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {

        try {
            curConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void openConnection() {
        if (curConnection == null) {
            try {
                Connection connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
