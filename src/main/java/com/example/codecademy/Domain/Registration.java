package com.example.codecademy.Domain;

import java.util.ArrayList;
import java.util.Date;
public class Registration {
    private Date registrationDate;
    private String emailAddress;
    private String courseName;
    private int certificateID;
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(int certificateID) {
        this.certificateID = certificateID;
    }


    @Override
    public String toString() {
        return "Registration{" +
                "registrationDate=" + registrationDate +
                ", emailAddress='" + emailAddress + '\'' +
                ", courseName='" + courseName + '\'' +
                ", certificateID=" + certificateID +
                '}';
    }
}