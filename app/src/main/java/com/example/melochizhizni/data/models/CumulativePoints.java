package com.example.melochizhizni.data.models;

public class CumulativePoints {
    private String nameUser;
    private String phoneNumber;
    private int points;

    public CumulativePoints(String nameUser, String phoneNumber, int points) {
        this.nameUser = nameUser;
        this.phoneNumber = phoneNumber;
        this.points = points;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
