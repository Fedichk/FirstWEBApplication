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

@WebServlet("/newreview")
public class NewReviewController extends HttpServlet {

    private ReviewRepository repository;

    @Override
    public void init() throws ServletException {
        repository = (ReviewRepository) getServletContext().getAttribute("repository");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Review review = new Review();
        review.setAuthorName(req.getParameter("name"));
        review.setText(req.getParameter("text"));
        review.setGrade(Integer.parseInt(req.getParameter("grade")));
        try {
            repository.addReview(review);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }
        resp.sendRedirect("/allreviews");
    }
}