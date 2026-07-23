package com.pestdetect.model;

/** Plain data object (POJO) representing one row of the "farmer" table. */
public class Farmer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    public Farmer() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
