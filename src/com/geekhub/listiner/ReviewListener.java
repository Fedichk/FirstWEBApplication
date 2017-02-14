package com.geekhub.listiner;

import com.geekhub.dao.ReviewRepository;
import org.apache.commons.dbcp.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class ReviewListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties properties = new Properties();
        BasicDataSource dataSource = new BasicDataSource();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/db.properties");
            properties.load(inputStream);
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Comment", e);
        }

        ReviewRepository repository = new ReviewRepository(dataSource);
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("repository", repository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Destroyed");
    }
}
