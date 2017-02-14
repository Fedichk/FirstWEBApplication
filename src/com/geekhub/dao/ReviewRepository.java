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

    public void addReview(Review review) throws SQLException {
        String sql = "INSERT INTO reviews VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, review.getAuthorName());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3, review.getGrade());
            preparedStatement.executeUpdate();
    }

    public List<Review> getAllReviews() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT `date`, `name`, `grade` FROM reviews";
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Review review = new Review();
                review.setDate(resultSet.getDate("date").toLocalDate());
                review.setAuthorName(resultSet.getString("name"));
                review.setGrade(resultSet.getInt("grade"));
                reviews.add(review);
            }
        return reviews;
    }
}