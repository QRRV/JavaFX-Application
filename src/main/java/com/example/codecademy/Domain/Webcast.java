package com.example.codecademy.Domain;

import java.util.Date;

public class Webcast {
    private int webcastID;
    private String title;
    private String URL;
    private Date date;
    private String nameSpeaker;
    private String organisation;
    private String description;
    private Integer percentage;

    public int getWebcastID() {
        return webcastID;
    }

    public void setWebcastID(int webcastID) {
        this.webcastID = webcastID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getNameSpeaker() {
        return nameSpeaker;
    }

    public void setNameSpeaker(String nameSpeaker) {
        this.nameSpeaker = nameSpeaker;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage(double percentage) {
        return percentage;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}