package com.geekhub.controller;

import com.geekhub.dao.ReviewRepository;
import com.geekhub.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newreview")
public class NewReviewController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReviewRepository repository = new ReviewRepository();
        Review review = new Review();
        review.setAuthorName(req.getParameter("name"));
        review.setText(req.getParameter("text"));
        review.setGrade(Integer.parseInt(req.getParameter("grade")));
        repository.addReview(review);

        resp.sendRedirect("/allreviews");
    }
}