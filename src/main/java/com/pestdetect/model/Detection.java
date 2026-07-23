package com.pestdetect.model;

/** POJO for the "detection_history" table (plus farmerName for admin views). */
public class Detection {
    private int id;
    private int farmerId;
    private Integer pestId;      // may be null
    private String pestName;
    private double confidence;
    private String imagePath;
    private String detectedAt;
    private String farmerName;   // filled only when listing for admin

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getFarmerId() { return farmerId; }
    public void setFarmerId(int farmerId) { this.farmerId = farmerId; }
    public Integer getPestId() { return pestId; }
    public void setPestId(Integer pestId) { this.pestId = pestId; }
    public String getPestName() { return pestName; }
    public void setPestName(String pestName) { this.pestName = pestName; }
    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public String getDetectedAt() { return detectedAt; }
    public void setDetectedAt(String detectedAt) { this.detectedAt = detectedAt; }
    public String getFarmerName() { return farmerName; }
    public void setFarmerName(String farmerName) { this.farmerName = farmerName; }
}
