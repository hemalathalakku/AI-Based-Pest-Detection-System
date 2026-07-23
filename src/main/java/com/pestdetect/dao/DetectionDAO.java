package com.pestdetect.dao;

import com.pestdetect.model.Detection;
import com.pestdetect.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetectionDAO {

    public void save(Detection d) throws SQLException {
        String sql = "INSERT INTO detection_history(farmer_id,pest_id,pest_name,confidence,image_path) " +
                     "VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getFarmerId());
            if (d.getPestId() == null) ps.setNull(2, Types.INTEGER);
            else ps.setInt(2, d.getPestId());
            ps.setString(3, d.getPestName());
            ps.setDouble(4, d.getConfidence());
            ps.setString(5, d.getImagePath());
            ps.executeUpdate();
        }
    }

    /** History for one farmer (newest first). */
    public List<Detection> findByFarmer(int farmerId) throws SQLException {
        List<Detection> list = new ArrayList<>();
        String sql = "SELECT * FROM detection_history WHERE farmer_id=? ORDER BY detected_at DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, farmerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs, false));
            }
        }
        return list;
    }

    /** All detections joined with farmer name (for admin "view uploaded images"). */
    public List<Detection> findAll() throws SQLException {
        List<Detection> list = new ArrayList<>();
        String sql = "SELECT d.*, f.name AS farmer_name FROM detection_history d " +
                     "JOIN farmer f ON d.farmer_id=f.id ORDER BY d.detected_at DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs, true));
        }
        return list;
    }

    public int count() throws SQLException { return scalar("SELECT COUNT(*) FROM detection_history"); }
    public int countToday() throws SQLException {
        return scalar("SELECT COUNT(*) FROM detection_history WHERE DATE(detected_at)=CURDATE()");
    }
    public int countThisMonth() throws SQLException {
        return scalar("SELECT COUNT(*) FROM detection_history " +
                "WHERE YEAR(detected_at)=YEAR(CURDATE()) AND MONTH(detected_at)=MONTH(CURDATE())");
    }

    /** Top pests by number of detections -> each row is {pestName, count}. */
    public List<String[]> mostCommon() throws SQLException {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT pest_name, COUNT(*) c FROM detection_history " +
                     "GROUP BY pest_name ORDER BY c DESC LIMIT 5";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                list.add(new String[]{ rs.getString("pest_name"), String.valueOf(rs.getInt("c")) });
        }
        return list;
    }

    private int scalar(String sql) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private Detection map(ResultSet rs, boolean withFarmerName) throws SQLException {
        Detection d = new Detection();
        d.setId(rs.getInt("id"));
        d.setFarmerId(rs.getInt("farmer_id"));
        int pid = rs.getInt("pest_id");
        d.setPestId(rs.wasNull() ? null : pid);
        d.setPestName(rs.getString("pest_name"));
        d.setConfidence(rs.getDouble("confidence"));
        d.setImagePath(rs.getString("image_path"));
        d.setDetectedAt(String.valueOf(rs.getTimestamp("detected_at")));
        if (withFarmerName) d.setFarmerName(rs.getString("farmer_name"));
        return d;
    }
}
