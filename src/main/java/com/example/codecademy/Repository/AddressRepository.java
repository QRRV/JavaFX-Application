package com.example.codecademy.Repository;

import com.example.codecademy.Domain.Address;
import com.example.codecademy.Domain.Student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddressRepository {
    public static boolean deleteAddress(String studentEmail){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM Address WHERE emailAddress =  '" + studentEmail + "'" +
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
    public static ArrayList<Address> getAddresses() {
        String selectStmt = "SELECT * FROM address";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Address> addresses = new ArrayList<>();
            while (rsEmp.next()) {
                Address returningAddress = new Address();
                returningAddress.setEmailAddress(rsEmp.getString("emailAddress"));
                returningAddress.setStreetName(rsEmp.getString("streetName"));
                returningAddress.setHouseNumber(Integer.valueOf(rsEmp.getString("houseNumber")));
                returningAddress.setHouseNumberAdd(rsEmp.getString("houseNumberAdd"));
                returningAddress.setZipCode(rsEmp.getString("zipCode"));
                returningAddress.setCity(rsEmp.getString("city"));
                returningAddress.setCountry(rsEmp.getString("country"));
                addresses.add(returningAddress);
            }
            return addresses;
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
    public static Address getAddressByEmail(String studentEmail) {
        String selectStmt = "SELECT * FROM address WHERE emailAddress = '" + studentEmail + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            Address returningAddress = new Address();
            while (rsEmp.next()) {
                returningAddress.setEmailAddress(rsEmp.getString("emailAddress"));
                returningAddress.setStreetName(rsEmp.getString("streetName"));
                returningAddress.setHouseNumber(Integer.valueOf(rsEmp.getString("houseNumber")));
                returningAddress.setHouseNumberAdd(rsEmp.getString("houseNumberAdd"));
                returningAddress.setZipCode(rsEmp.getString("zipCode"));
                returningAddress.setCity(rsEmp.getString("city"));
                returningAddress.setCountry(rsEmp.getString("country"));
            }
            return returningAddress;
        } catch (SQLException e) {
            System.out.println("While selecting addresses by email, an error occurred: " + e);
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

    public static boolean createAddress(String studentEmail, String streetName, int houseNumber, String houseNumberAdd, String zipCode, String city, String country) {
        String updateStmt =
                "BEGIN\n" + "INSERT INTO address VALUES('"+ studentEmail + "','"+streetName+"',"+ houseNumber +",'"+ houseNumberAdd +"','"+zipCode+"','"+ city +"','"+ country +"')\n"+
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
    public static Boolean updateAddress(String studentEmailPK, String studentEmail, String streetName, int houseNumber, String houseNumberAdd, String zipCode, String city, String country) {
        String updateStmt =
                "BEGIN\n" + "UPDATE address set emailAddress = '"+studentEmail+"',streetName = '"+streetName+"',houseNumber = '"+houseNumber+"',houseNumberAdd = '"+houseNumberAdd+"',zipCode = '"+zipCode+"',city = '"+city+"',country = '"+country+"' WHERE emailAddress =  '" + studentEmailPK + "'" +
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
