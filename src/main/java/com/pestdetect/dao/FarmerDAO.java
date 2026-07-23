package com.pestdetect.dao;

import com.pestdetect.model.Farmer;
import com.pestdetect.util.DBConnection;
import com.pestdetect.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** All database operations for farmers live here (Data Access Object). */
public class FarmerDAO {

    /** Insert a new farmer. Returns false if the email already exists. */
    public boolean register(Farmer f) throws SQLException {
        if (findByEmail(f.getEmail()) != null) return false;
        String sql = "INSERT INTO farmer(name,email,password,phone,address) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getName());
            ps.setString(2, f.getEmail());
            ps.setString(3, PasswordUtil.hash(f.getPassword()));
            ps.setString(4, f.getPhone());
            ps.setString(5, f.getAddress());
            return ps.executeUpdate() == 1;
        }
    }

    /** Used at login: returns the farmer only if email+password match. */
    public Farmer login(String email, String password) throws SQLException {
        Farmer f = findByEmail(email);
        if (f != null && f.getPassword().equals(PasswordUtil.hash(password))) return f;
        return null;
    }

    public Farmer findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM farmer WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public Farmer findById(int id) throws SQLException {
        String sql = "SELECT * FROM farmer WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public void updateProfile(Farmer f) throws SQLException {
        String sql = "UPDATE farmer SET name=?, phone=?, address=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getName());
            ps.setString(2, f.getPhone());
            ps.setString(3, f.getAddress());
            ps.setInt(4, f.getId());
            ps.executeUpdate();
        }
    }

    public List<Farmer> findAll() throws SQLException {
        List<Farmer> list = new ArrayList<>();
        String sql = "SELECT * FROM farmer ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public void delete(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM farmer WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int count() throws SQLException {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM farmer")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /** Copy one ResultSet row into a Farmer object. */
    private Farmer map(ResultSet rs) throws SQLException {
        Farmer f = new Farmer();
        f.setId(rs.getInt("id"));
        f.setName(rs.getString("name"));
        f.setEmail(rs.getString("email"));
        f.setPassword(rs.getString("password"));
        f.setPhone(rs.getString("phone"));
        f.setAddress(rs.getString("address"));
        return f;
    }
}
