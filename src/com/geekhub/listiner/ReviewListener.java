package com.geekhub.listiner;

import com.geekhub.dao.ReviewRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ReviewListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource dataSource = null;
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/reviews");
        } catch (NamingException e) {
            e.printStackTrace();
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
