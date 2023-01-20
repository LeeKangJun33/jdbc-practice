package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {


    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    public static final String DB_NAME = "jun";
    public static final String DB_PASSWORD = "";
    public static final int MAX_POOL_SIZE = 40;

    private static final DataSource da;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername(DB_NAME);
        hikariDataSource.setPassword(DB_PASSWORD);
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE); //마냥 높게 잡는다고해서 좋은건아님.
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        da = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return da.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static  DataSource getDataSource(){
        return da;
    }
}
