package com.app.dao;

import com.app.util.DbUtil;
import java.sql.*;

public class QuizAttemptsDao implements AutoCloseable {

    private Connection con;

    public QuizAttemptsDao() throws SQLException {
        con = DbUtil.getConnection();
    }

    public boolean hasAttempted(int studentId, int quizId) throws SQLException {

        String sql = "SELECT attempt_id FROM quiz_attempts WHERE student_id=? AND quiz_id=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, quizId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
    public void saveAttempt(int studentId, int quizId, int score, int total) throws SQLException {

        String sql = "INSERT INTO quiz_attempts (student_id, quiz_id, final_score, total_questions) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.setInt(4, total);
            stmt.executeUpdate();
        }
    }

    public List<String> getQuizScores(int quizId) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = """
            SELECT u.name, qa.final_score, qa.total_questions
				FROM quiz_attempts qa
				JOIN users u ON qa.student_id = u.user_id
				WHERE qa.quiz_id = ?

        """;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add("Student: " + rs.getString("name") +
                         " | Score: " + rs.getInt("final_score") +
                         "/" + rs.getInt("total_questions"));
            }
        }
        return list;
    }

    @Override
    public void close() throws SQLException {
        if (con != null)
            con.close();
    }
}
