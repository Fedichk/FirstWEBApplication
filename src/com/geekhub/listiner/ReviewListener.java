package com.geekhub.listiner;

import com.geekhub.dao.ReviewRepository;
import com.geekhub.util.DataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReviewListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource dataSource = new DataSource();
        ReviewRepository repository = new ReviewRepository(dataSource);
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("repository", repository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Destroyed");
    }
}
