package com.geekhub.dao;

import com.geekhub.model.Review;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private BasicDataSource dataSource;

    public ReviewRepository(BasicDataSource dataSource){
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