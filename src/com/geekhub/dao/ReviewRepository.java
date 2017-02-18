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

    public ReviewRepository(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addReview(Review review) throws SQLException {
        String sql = "INSERT INTO reviews VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, review.getAuthorName());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3, review.getGrade());
            preparedStatement.executeUpdate();
        }
    }

    public List<Review> getAllReviews(int page, int offset) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        int start = page * offset;
        String sql = "SELECT `date`, `name`, `grade` FROM reviews ORDER BY `date` DESC LIMIT " + start + "," + offset;
        try (Statement statement = dataSource.getConnection().createStatement()) {
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

    public int getPages(int offset) throws SQLException {
        String sql = "SELECT COUNT(*) AS rowcount FROM reviews";
        try (Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int count = resultSet.getInt("rowcount");
            return calculatePages(count, offset);
        }
    }

    private int calculatePages(int count, int offset) {
        int pages;
        if (((count % offset) == 0) || (count > 0 && count < offset)) {
            pages = count / offset;
        } else {
            pages = count / offset + 1;
        }
        return pages;
    }
}