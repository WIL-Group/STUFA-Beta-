package com.example.stufa.data_models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable
{
    private String title;
    private String message;
    private Date date;
    private boolean viewed;
    private Staff staff;
    private String key;

    public Announcement() {
    }

    public Announcement(String title, String message, Date date, Staff staff) {
        this.title = title;
        this.message = message;
        this.date = date;
        this.staff = staff;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
