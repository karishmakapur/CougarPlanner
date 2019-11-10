package com.example.myfirstapp;

import java.util.Date;

public class User {
    private String name;
    private String password;
    private String uniname;
    private String email;
    private String dob;
    private String gradyr;
    private String currYear;

    public User() {
    }

    public User(String name, String password, String uniname, String email, String dob, String gradyr) {
        this.name = name;
        this.password = password;
        this.uniname = uniname;
        this.email = email;
        this.dob = dob;
        this.gradyr = gradyr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniname() {
        return uniname;
    }

    public void setUniname(String uniname) {
        this.uniname = uniname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGradyr() {
        return gradyr;
    }

    public void setGradyr(String gradyr) {
        this.gradyr = gradyr;
    }

    public String getCurrYear() {
        return currYear;
    }

    public void setCurrYear(String currYear) {
        this.currYear = currYear;
    }
}
