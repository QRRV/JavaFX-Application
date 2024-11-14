package com.example.codecademy.Repository;
import com.example.codecademy.Domain.*;

import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class CourseRepository {
    public static boolean deleteCourse(String courseID){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM Course WHERE courseName =  '" + courseID + "'" +
                        "END;";
        //Execute UPDATE operation
        try {
            DbUtil.dbExecuteUpdate(updateStmt);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<Course> getCourses() {
        String selectStmt = "SELECT * FROM course";
        String recSelectStmt = "SELECT * FROM recommendedCourse";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ResultSet recRes = DbUtil.dbExecuteQuery(recSelectStmt);
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ArrayList<Course> courses = new ArrayList<>();
            while (rsEmp.next()) {
                Course returningCourse = new Course();
                returningCourse.setCourseName(rsEmp.getString("courseName"));
                returningCourse.setSubject(rsEmp.getString("subject"));
                returningCourse.setIntroductionText(rsEmp.getString("introductionText"));
                returningCourse.setLevel(rsEmp.getString("status"));

                courses.add(returningCourse);
            }
            //Return employee object
            while (recRes.next()){
                courses.forEach(course -> {

                    try {
                        if (recRes.getString("courseName").equals(course.getCourseName())){
                            course.setInterest(recRes.getString("recommendedCourse"));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("While selecting all courses, an error occurred: " + e);
            //Return exception
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Course> getTopCompletedCourses() {
        String selectStmt = "SELECT * FROM course WHERE courseName IN (SELECT TOP 3 courseName FROM registration GROUP BY courseName ORDER BY courseName ASC)";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTCC = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Course> courses = new ArrayList<>();
            while (rsTCC.next()) {
                Course returningCourse = new Course();
                returningCourse.setCourseName(rsTCC.getString("courseName"));
                returningCourse.setSubject(rsTCC.getString("subject"));
                returningCourse.setIntroductionText(rsTCC.getString("introductionText"));
                returningCourse.setLevel(rsTCC.getString("status"));

                courses.add(returningCourse);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("While selecting top courses, an error occurred: " + e);
            //Return exception
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getModulesTitle() {
        String selectStmt = "SELECT * FROM module";
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsMod = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<String> modulesTitle = new ArrayList<>();
            while (rsMod.next()) {
                String returningModule;
                returningModule = rsMod.getString("title");
                modulesTitle.add(returningModule);
            }
            return modulesTitle;
        } catch (SQLException e) {
            System.out.println("While selecting all modules, an error occurred: " + e);
            //Return exception
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getCountCompleted(String courseName){
        int amount=0;
        String timesCompletedStmt = "SELECT COUNT(certificateID)\n" +
                "FROM registration\n" +
                "WHERE courseName='"+courseName+"'";
        try {
            ResultSet count = DbUtil.dbExecuteQuery(timesCompletedStmt);
            while (count.next()){
                amount = count.getInt(1);
            }
            return  amount;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    public static Course getCourseByCourseName(String courseName) {
        String selectStmt = "SELECT * FROM course WHERE courseName = '" + courseName + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            while (rsEmp.next()) {
                Course returningCourse = new Course();
                returningCourse.setCourseName(rsEmp.getString("courseName"));
                returningCourse.setSubject(rsEmp.getString("subject"));
                returningCourse.setIntroductionText(rsEmp.getString("introductionText"));
                returningCourse.setLevel(rsEmp.getString("status"));
                return returningCourse;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("While selecting registrations by email, an error occurred: " + e);
            //Return exception
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getModuleID(String mTitle) {
        String selectStmt = "SELECT moduleID FROM module WHERE title='"+ mTitle +"'";
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsMod = DbUtil.dbExecuteQuery(selectStmt);
            Integer returningModuleID = 1;
            while (rsMod.next()) {
                returningModuleID = rsMod.getInt("moduleID");
            }
            return returningModuleID;
        } catch (SQLException e) {
            System.out.println("While selecting all modules, an error occurred: " + e);
            //Return exception
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean createContentItem(String courseName, Integer moduleID){
        String updateStmt =
                "BEGIN\n" + "INSERT INTO [content-item] (status, courseName, moduleID) VALUES('Active','"+ courseName + "','" + moduleID + "')\n"+
                        "END;";
        try {
            DbUtil.dbExecuteUpdate(updateStmt);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while executing query: " + e);
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createCourse(String courseID, String subject, String status, String introductionText, String recommendation) {
        String updateStmt =
                "BEGIN\n" + "INSERT INTO Course VALUES('"+ courseID + "','"+subject+"','"+ status +"','"+ introductionText + "')\n"+
                        "INSERT INTO recommendedCourse VALUES('"+courseID+"','"+recommendation+"')\n"+
                        "END;";
        //Execute DELETE operation
        try {
            DbUtil.dbExecuteUpdate(updateStmt);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//

    }
    public static Boolean updateCourse(String courseID, String subject, String status, String introductionText, String recommendation) {
        String updateStmt =
                "BEGIN\n" + "UPDATE Course set subject = '"+subject+"',status = '"+status+"',introductionText = '"+introductionText+"' WHERE courseName =  '" + courseID + "'" +
                        "   COMMIT;\n" +
                        "UPDATE recommendedCourse set recommendedCourse = '"+recommendation+"' WHERE courseName =  '" + courseID + "'" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DbUtil.dbExecuteUpdate(updateStmt);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
