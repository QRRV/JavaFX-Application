package com.example.codecademy.Domain;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private String subject;
    private String introductionText;
    private String level;
    private String interest;
    private int timesCompleted;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getTimesCompleted() { return timesCompleted; }

    public void setTimesCompleted(int timesCompleted) { this.timesCompleted = timesCompleted; }

    @Override
    public String toString() {
        return courseName +" " + subject + " " + introductionText +" " + level + " " + interest+" "+timesCompleted;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Course){
            Course c = (Course) o;
            return this.courseName.equals(c.getCourseName());
        } else
            return false;
    }
}
