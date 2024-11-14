package com.example.codecademy.Repository;

import com.example.codecademy.Domain.Module;
import com.example.codecademy.Domain.Webcast;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WebcastRepository {
    public static boolean deleteWebcast(int webcastID){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM webcast WHERE webcastID =  '" + webcastID + "'" +
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
    public static ArrayList<Webcast> getWebcasts() {
        String selectStmt = "SELECT * FROM webcast";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<Webcast> webcasts = new ArrayList<>();
            while (rsEmp.next()) {
                Webcast returningWebcast = new Webcast();
                returningWebcast.setWebcastID(rsEmp.getInt("webcastID"));
                returningWebcast.setTitle(rsEmp.getString("title"));
                returningWebcast.setURL(rsEmp.getString("URL"));
                returningWebcast.setDate(Date.valueOf(rsEmp.getString("date")));
                returningWebcast.setNameSpeaker(rsEmp.getString("nameSpeaker"));
                returningWebcast.setOrganisation(rsEmp.getString("organisation"));
                returningWebcast.setDescription(rsEmp.getString("description"));
                webcasts.add(returningWebcast);
            }
            return webcasts;
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
    public static Webcast getWebcastByID(int webcastID) {
        String selectStmt = "SELECT * FROM webcast WHERE webcastID = " + webcastID;
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            while (rsEmp.next()) {
                Webcast returningWebcast = new Webcast();
                returningWebcast.setWebcastID(rsEmp.getInt("webcastID"));
                returningWebcast.setTitle(rsEmp.getString("title"));
                returningWebcast.setURL(rsEmp.getString("URL"));
                returningWebcast.setDate(Date.valueOf(rsEmp.getString("date")));
                returningWebcast.setNameSpeaker(rsEmp.getString("nameSpeaker"));
                returningWebcast.setOrganisation(rsEmp.getString("organisation"));
                returningWebcast.setDescription(rsEmp.getString("description"));
                return returningWebcast;
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


    public static boolean createWebcast(String title, String URL, String date, String nameSpeaker, String organisation, String description) {
        String updateStmt =
                "BEGIN\n" + "INSERT INTO webcast VALUES('"+ title + "','"+URL+"','"+ date +"','"+ nameSpeaker + "','"+ organisation + "','"+ description + "')\n"+
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
    public static Boolean updateWebcast(int webcastID, String title, String URL, String date, String nameSpeaker, String organisation, String description) {
        SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
        Date date2 = Date.valueOf(date);
        String updateStmt =
                "BEGIN\n" + "UPDATE module set title = '"+title+"',URL = '"+URL+"',date = '"+date2+"',nameSpeaker = '"+nameSpeaker+"',organisation = '"+organisation+"',description = '"+description+"' WHERE webcastID =  '" + webcastID + "'" +
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
