package com.geekhub.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private Connection connection;

    public DbUtil() {
        BasicDataSource dataSource = new BasicDataSource();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/db.properties"));
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            this.connection = dataSource.getConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}