package com.geekhub.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private Connection connection;

    public DataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        Properties properties = new Properties();
        try {
            properties.load(DataSource.class.getClassLoader()
                                            .getResourceAsStream("/db.properties"));
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