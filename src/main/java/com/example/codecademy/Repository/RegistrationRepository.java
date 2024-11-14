package com.example.codecademy.Repository;

import com.example.codecademy.Domain.Address;
import com.example.codecademy.Domain.Registration;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RegistrationRepository {
    public static boolean deleteRegistration(String studentEmail, String courseName, String registrationDate){
        Date date = Date.valueOf(registrationDate);
        String updateStmt =
                "BEGIN\n" + "DELETE FROM registration WHERE emailAddress =  '" + studentEmail + "'" + "AND courseName = '" + courseName + "' AND registrationDate = '" + date + "'" +
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
    public static ArrayList<Registration> getRegistrations() {
        String selectStmt = "SELECT * FROM registration";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Registration> registrations = new ArrayList<>();
            while (rsEmp.next()) {
                Registration returningRegistration = new Registration();
                returningRegistration.setCourseName(rsEmp.getString("courseName"));
                returningRegistration.setEmailAddress(rsEmp.getString("emailAddress"));
                returningRegistration.setRegistrationDate(Date.valueOf(rsEmp.getString("registrationDate")));
                if(rsEmp.getString("certificateID") != null) {
                    returningRegistration.setCertificateID(Integer.parseInt(rsEmp.getString("certificateID")));
                }
                registrations.add(returningRegistration);
            }
            return registrations;
        } catch (SQLException e) {
            System.out.println("While selecting all registrations, an error occurred: " + e);
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
    public static ArrayList<Registration> getRegistrationsByEmail(String studentEmail) {
        String selectStmt = "SELECT * FROM registration WHERE emailAddress = '" + studentEmail + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Registration> registrations = new ArrayList<>();
            while (rsEmp.next()) {
                Registration returningRegistration = new Registration();
                returningRegistration.setEmailAddress(rsEmp.getString("emailAddress"));
                returningRegistration.setCourseName(rsEmp.getString("courseName"));
                returningRegistration.setRegistrationDate(Date.valueOf(rsEmp.getString("registrationDate")));
                if(rsEmp.getString("certificateID") != null) {
                    returningRegistration.setCertificateID(Integer.parseInt(rsEmp.getString("certificateID")));
                }
                registrations.add(returningRegistration);
            }
            return registrations;
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
    public static Registration getRegistrationsByEmailAndCourse(String studentEmail, String courseName) {
        String selectStmt = "SELECT * FROM registration WHERE emailAddress = '" + studentEmail + "' AND courseName = '" + courseName + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            Registration registration = null;
            while (rsEmp.next()) {
                Registration returningRegistration = new Registration();
                returningRegistration.setEmailAddress(rsEmp.getString("emailAddress"));
                returningRegistration.setCourseName(rsEmp.getString("courseName"));
                returningRegistration.setRegistrationDate(Date.valueOf(rsEmp.getString("registrationDate")));
                if(rsEmp.getString("certificateID") != null) {
                    returningRegistration.setCertificateID(Integer.parseInt(rsEmp.getString("certificateID")));
                }
                registration = returningRegistration;
            }
            return registration;
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

    public static int getNumberOfRegistrationsByCourse(String courseName) {
        String selectStmt = "SELECT COUNT(*) as count FROM(\n" +
                "SELECT emailAddress FROM registration WHERE courseName = '" + courseName +"'\n" +
                "GROUP BY emailAddress) as Z";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            int count = 0;
            while(rsEmp.next()){
                count = rsEmp.getInt("count");
            }
            return count;
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

    public static boolean createRegistration(String studentEmail, String courseName, String registrationDate, String certificateID) {
        Date date = Date.valueOf(registrationDate);
        String updateStmt;
        if(certificateID != null){
            updateStmt =
                    "BEGIN\n" + "INSERT INTO registration VALUES('"+ courseName + "','"+studentEmail+"','"+ date +"','"+ certificateID + "')\n"+
                            "END;";
        }else{
            updateStmt =
                    "BEGIN\n" + "INSERT INTO registration VALUES('"+ courseName + "','"+studentEmail+"','"+ date +"', null)\n"+
                            "END;";
        }



        //Execute DELETE operation
        try {
            DbUtil.dbExecuteUpdate(updateStmt);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static Boolean updateRegistration(String studentEmailPK, String studentEmail, String courseName, String registrationDate) {
        Date date = Date.valueOf(registrationDate);
        String updateStmt =
                "BEGIN\n" + "UPDATE registration set emailAddress = '" + studentEmail + "',courseName = '" + courseName + "',registrationDate = '" + date + "' WHERE emailAddress =  '" + studentEmailPK + "'" +
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
