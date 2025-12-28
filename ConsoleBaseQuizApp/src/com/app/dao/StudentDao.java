package com.app.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.Users;
import com.app.util.DbUtil;

public class StudentDao implements AutoCloseable {

    private Connection con;

    public StudentDao() throws SQLException {
        con = DbUtil.getConnection();
    }

    public int studentLogin(String email, String password) throws SQLException {

        String sql = "SELECT user_id FROM users WHERE email=? AND password_hash=? AND role='student'";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return rs.getInt(1);
        }
        return -1;
    }
    
    @Override
    public void close() throws SQLException {
        if (con != null)
            con.close();
    }
}
