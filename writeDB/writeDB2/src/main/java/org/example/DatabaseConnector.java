package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// 数据库连接器，用于管理数据库连接
public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testlon2?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "1234567";

    // 数据库连接对象
    private Connection connection;

    public DatabaseConnector() {
        // 构造函数，初始化数据库连接
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        // 获取数据库连接对象
        return connection;
    }

    public void closeConnection() {
        // 关闭数据库连接
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close database connection: " + e.getMessage());
            }
        }
    }
}