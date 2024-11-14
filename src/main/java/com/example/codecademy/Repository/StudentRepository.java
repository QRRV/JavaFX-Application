package com.example.codecademy.Repository;

import com.example.codecademy.Domain.Course;
import com.example.codecademy.Domain.Student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StudentRepository {
    public static boolean deleteStudent(String studentEmail){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM student WHERE emailAddress =  '" + studentEmail + "'" +
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
    public static ArrayList<Student> getStudents() {
        String selectStmt = "SELECT * FROM student";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Student> students = new ArrayList<>();
            while (rsEmp.next()) {
                Student returningStudent = new Student();
                returningStudent.setEmailAddress(rsEmp.getString("emailAddress"));
                returningStudent.setName(rsEmp.getString("name"));
                returningStudent.setBirthDay(Date.valueOf(rsEmp.getString("birthday")));
                returningStudent.setGender(rsEmp.getString("gender"));
                students.add(returningStudent);
            }
            //Return employee object
            return students;
        } catch (SQLException e) {
            System.out.println("While selecting all students, an error occurred: " + e);
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
    public static ArrayList<String> getStudentsEmailsByGender(String gender) {
        String selectStmt = "SELECT emailAddress FROM student WHERE gender = '" + gender + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<String> emails = new ArrayList<>();
            while (rsEmp.next()) {
                emails.add(rsEmp.getString("emailAddress"));
            }
            //Return employee object
            return emails;
        } catch (SQLException e) {
            System.out.println("While selecting all students, an error occurred: " + e);
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

    public static boolean createStudent(String studentEmail, String name, String birthDay, String gender) {
        SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
        Date date = Date.valueOf(birthDay);
        String updateStmt =
                "BEGIN\n" + "INSERT INTO Student VALUES('"+ studentEmail + "','"+name+"','"+ date +"','"+ gender +"')\n"+
                        "END;";


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

//
    }
    public static Boolean updateStudent(String studentEmailPK,String studentEmail, String name, String birthDay, String gender) {
        SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
        Date date = Date.valueOf(birthDay);
        String updateStmt =
                "BEGIN\n" + "UPDATE student set emailAddress = '"+studentEmail+"',name = '"+name+"',birthday = '"+date+"',gender = '"+gender+"' WHERE emailAddress =  '" + studentEmailPK + "'" +
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
