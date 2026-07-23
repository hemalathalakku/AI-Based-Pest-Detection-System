package com.pestdetect.dao;

import com.pestdetect.model.Pest;
import com.pestdetect.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PestDAO {

    public Pest findByName(String name) throws SQLException {
        String sql = "SELECT * FROM pest WHERE pest_name=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Pest> findAll() throws SQLException {
        List<Pest> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM pest ORDER BY pest_name")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public void add(Pest p) throws SQLException {
        String sql = "INSERT INTO pest(pest_name,crop_name,symptoms,causes,treatment,prevention) " +
                     "VALUES(?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getPestName());
            ps.setString(2, p.getCropName());
            ps.setString(3, p.getSymptoms());
            ps.setString(4, p.getCauses());
            ps.setString(5, p.getTreatment());
            ps.setString(6, p.getPrevention());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM pest WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int count() throws SQLException {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM pest")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private Pest map(ResultSet rs) throws SQLException {
        Pest p = new Pest();
        p.setId(rs.getInt("id"));
        p.setPestName(rs.getString("pest_name"));
        p.setCropName(rs.getString("crop_name"));
        p.setSymptoms(rs.getString("symptoms"));
        p.setCauses(rs.getString("causes"));
        p.setTreatment(rs.getString("treatment"));
        p.setPrevention(rs.getString("prevention"));
        return p;
    }
}
