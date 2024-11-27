package com.example.a1stexample;

public class RoomRequest {
    private String username;
    private String year;
    private String branch;
    private String email;
    private String mobile;

    // Constructor, getters, and setters
    public RoomRequest(String username, String year, String branch, String email, String mobile) {
        this.username = username;
        this.year = year;
        this.branch = branch;
        this.email = email;
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
