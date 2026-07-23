package com.pestdetect.model;

/** POJO for the "pest" knowledge-base table. */
public class Pest {
    private int id;
    private String pestName;
    private String cropName;
    private String symptoms;
    private String causes;
    private String treatment;
    private String prevention;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPestName() { return pestName; }
    public void setPestName(String pestName) { this.pestName = pestName; }
    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getCauses() { return causes; }
    public void setCauses(String causes) { this.causes = causes; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public String getPrevention() { return prevention; }
    public void setPrevention(String prevention) { this.prevention = prevention; }
}
