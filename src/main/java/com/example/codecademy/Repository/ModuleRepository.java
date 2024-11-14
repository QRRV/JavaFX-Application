package com.example.codecademy.Repository;

import com.example.codecademy.Domain.Address;
import com.example.codecademy.Domain.Course;
import com.example.codecademy.Domain.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModuleRepository {
    public static boolean deleteModule(String moduleID){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM Module WHERE moduleID =  '" + moduleID + "'" +
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
    public static ArrayList<Module> getModules() {
        String selectStmt = "SELECT * FROM module";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Module> modules = new ArrayList<>();
            while (rsEmp.next()) {
                Module returningModule = new Module();
                returningModule.setModuleID(rsEmp.getInt("moduleID"));
                returningModule.setTitle(rsEmp.getString("title"));
                returningModule.setVersion(Integer.valueOf(rsEmp.getString("version")));
                returningModule.setDescription(rsEmp.getString("description"));
                returningModule.setEmailContactPerson(rsEmp.getString("emailContactPerson"));
                modules.add(returningModule);
            }
            return modules;
        } catch (SQLException e) {
            System.out.println("While selecting all addresses, an error occurred: " + e);
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

    public static Module getModuleByID(int moduleID) {
        String selectStmt = "SELECT * FROM module WHERE moduleID = " + moduleID;
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            while (rsEmp.next()) {
                Module returningModule = new Module();
                returningModule.setModuleID(rsEmp.getInt("moduleID"));
                returningModule.setTitle(rsEmp.getString("title"));
                returningModule.setVersion(Integer.valueOf(rsEmp.getString("version")));
                returningModule.setDescription(rsEmp.getString("description"));
                returningModule.setEmailContactPerson(rsEmp.getString("emailContactPerson"));
                return returningModule;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("While selecting all addresses, an error occurred: " + e);
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

    public static boolean createModule(String title, String version, String description, String emailContactPerson) {
        String updateStmt =
                "BEGIN\n" + "INSERT INTO module VALUES('"+ title + "','"+version+"','"+ description +"','"+ emailContactPerson + "')\n"+
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
    public static Boolean updateModule(int moduleID,String title, String version, String description, String emailContactPerson) {
        String updateStmt =
                "BEGIN\n" + "UPDATE module set title = '"+title+"',version = '"+version+"',description = '"+description+"',emailContactPerson = '"+emailContactPerson+"' WHERE moduleID =  '" + moduleID + "'" +
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
