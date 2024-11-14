package com.example.codecademy.Domain;

public class ContentItemProgress {
    private int contentItemID;
    private String emailAddress;
    private int percentage;

    public int getContentItemID() {
        return contentItemID;
    }

    public void setContentItemID(int contentItemProgressID) {
        this.contentItemID = contentItemProgressID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
