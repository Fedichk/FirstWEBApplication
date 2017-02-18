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
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, review.getAuthorName());
        preparedStatement.setString(2, review.getText());
        preparedStatement.setInt(3, review.getGrade());
        preparedStatement.executeUpdate();
    }

    public List<Review> getAllReviews(int counter) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        int start = counter * 5;
        String sql = "SELECT `date`, `name`, `grade` FROM reviews ORDER BY `date` DESC LIMIT " + start + ",5";
        Statement statement = dataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Review review = new Review();
            review.setDate(resultSet.getDate("date").toLocalDate());
            review.setAuthorName(resultSet.getString("name"));
            review.setGrade(resultSet.getInt("grade"));
            reviews.add(review);
        }
        statement.close();
        return reviews;
    }

    public int getPages() throws SQLException {
        String sql = "SELECT COUNT(*) AS rowcount FROM reviews";
        Statement statement = dataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        int count = resultSet.getInt("rowcount");
        statement.close();
        return calculatePages(count);
    }

    private int calculatePages(int count) {
        int pages = 0;
        if (((count % 5) == 0) || (count > 0 && count < 5)) {
            for (int i = 0; i < count / 5; i++) {
                pages = i;
            }
        } else {
            for (int i = 0; i < (count / 5 + 1); i++) {
                pages = i;
            }
        }
        return pages;
    }
}