package com.example.codecademy.Domain;

import java.util.Date;

public class ContentItem {
    private int ContentItemID;
    private Date date;
    private String status;
    private String courseName;
    private Integer moduleID;
    private Integer webcastID;

    public int getContentItemID() {
        return ContentItemID;
    }

    public void setContentItemID(int contentItemID) {
        ContentItemID = contentItemID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPercentage(double percentage) {
        return percentage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getModuleID() {
        return moduleID;
    }

    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    public Integer getWebcastID() {
        return webcastID;
    }

    public void setWebcastID(Integer webcastID) {
        this.webcastID = webcastID;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "ContentItemID=" + ContentItemID +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", courseName='" + courseName + '\'' +
                ", moduleID=" + moduleID +
                ", webcastID=" + webcastID +
                '}';
    }
}
