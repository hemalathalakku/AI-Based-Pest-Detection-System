package com.pestdetect.dao;

import com.pestdetect.model.Admin;
import com.pestdetect.util.DBConnection;
import com.pestdetect.util.PasswordUtil;

import java.sql.*;

public class AdminDAO {
    /** Returns the admin only if username + password match. */
    public Admin login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM admin WHERE username=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getString("password").equals(PasswordUtil.hash(password))) {
                    Admin a = new Admin();
                    a.setId(rs.getInt("id"));
                    a.setUsername(rs.getString("username"));
                    return a;
                }
            }
        }
        return null;
    }
}
