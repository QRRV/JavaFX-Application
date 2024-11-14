
package com.example.codecademy.Domain;
public class Module {
    private int moduleID;
    private String title;
    private int version;
    private String description;
    private String emailContactPerson;
    private Integer percentage;

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailContactPerson() {
        return emailContactPerson;
    }

    public void setEmailContactPerson(String emailContactPerson) {
        this.emailContactPerson = emailContactPerson;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}