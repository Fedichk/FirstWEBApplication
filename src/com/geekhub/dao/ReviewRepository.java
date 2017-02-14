package com.geekhub.dao;

import com.geekhub.model.Review;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private DataSource dataSource;

    public ReviewRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void addReview(Review review) {
        String sql = "INSERT INTO reviews VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
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
            Statement statement = dataSource.getConnection().createStatement();
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