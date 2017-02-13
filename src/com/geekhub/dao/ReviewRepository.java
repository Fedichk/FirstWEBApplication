package com.geekhub.dao;

import com.geekhub.model.Review;
import com.geekhub.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private DbUtil dbUtil;
    private Connection connection = dbUtil.getConnection();

    public ReviewRepository(DbUtil dbUtil){
        this.dbUtil = dbUtil;
    }

    public void addReview(Review review) {
        String sql = "INSERT INTO reviews VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, review.getAuthorName());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3, review.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT `date`, `name`, `grade` FROM reviews";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Review review = new Review();
                review.setDate(resultSet.getDate("date").toLocalDate());
                review.setAuthorName(resultSet.getString("name"));
                review.setGrade(resultSet.getInt("grade"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}