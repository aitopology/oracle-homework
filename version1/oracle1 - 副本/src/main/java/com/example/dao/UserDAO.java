package com.example.dao;

import com.example.entity.User;
import com.example.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO udata(username, password) VALUES(?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.executeUpdate();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM udata WHERE username=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE udata SET password=? WHERE username=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getUsername());
            pst.executeUpdate();
        }
    }

    public List<User> getAllUsers(int page, int pageSize) throws SQLException {
        String sql = "SELECT * FROM (SELECT a.*, ROWNUM rn FROM (SELECT * FROM udata ORDER BY username) a) WHERE rn BETWEEN ? AND ?";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            int start = (page - 1) * pageSize + 1;
            int end = page * pageSize;
            pst.setInt(1, start);
            pst.setInt(2, end);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("username"), rs.getString("password")));
            }
        }
        return users;
    }

    public int getTotalCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM udata";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}