package com.geekhub.controller;

import com.geekhub.dao.ReviewRepository;
import com.geekhub.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/allreviews")
public class AllReviewsController extends HttpServlet {

    private ReviewRepository repository;

    @Override
    public void init() throws ServletException {
        repository = (ReviewRepository) getServletContext().getAttribute("repository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Review> reviews;
        int limit = 5;
        int pages;
        int counter = Integer.parseInt(req.getParameter("page"));
        try {
            pages = repository.getPages(limit);
            int offset = (counter - 1) * limit;
            reviews = repository.getAllReviews(offset, limit);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.", e);
        }
        req.setAttribute("pages", pages);
        req.setAttribute("counter", counter);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("WEB-INF/view/viewall.jsp").forward(req, resp);
    }
}