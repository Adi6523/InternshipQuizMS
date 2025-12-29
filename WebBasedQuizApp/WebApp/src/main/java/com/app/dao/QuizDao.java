package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.model.Quizzes;
import com.app.util.DbUtil;

public class QuizDao implements AutoCloseable {

    private Connection connection;

    public QuizDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public int insertQuiz(Quizzes quiz) throws SQLException {
        String sql = "INSERT INTO quizzes (title, creator_id) VALUES (?, ?)";
        try (PreparedStatement stmt =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, quiz.getTitle());
            stmt.setInt(2, quiz.getCreator_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }

    public List<Quizzes> selectQuiz() throws SQLException {

        List<Quizzes> list = new ArrayList<>();
        String sql = "SELECT quiz_id, title FROM quizzes";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Quizzes q = new Quizzes();
                q.setQuiz_id(rs.getInt(1));
                q.setTitle(rs.getString(2));
                list.add(q);
            }
        }
        return list;
    }

    public List<Quizzes> getAvailableQuizzes(int studentId) throws SQLException {

        List<Quizzes> list = new ArrayList<>();

        String sql = """
            SELECT q.quiz_id, q.title
            FROM quizzes q
            WHERE q.quiz_id NOT IN
            (SELECT quiz_id FROM quiz_attempts WHERE student_id=?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quizzes q = new Quizzes();
                q.setQuiz_id(rs.getInt(1));
                q.setTitle(rs.getString(2));
                list.add(q);
            }
        }
        return list;
    }

    public void deleteQuiz(String title) throws SQLException {
        String sql = "DELETE FROM quizzes WHERE title=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null)
            connection.close();
    }
}
