package com.example.codecademy.Repository;

import com.example.codecademy.Domain.ContentItem;
import com.example.codecademy.Domain.Module;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ContentItemRepository {
    public static boolean deleteContentItem(String contentItemID){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM content-item WHERE contentItemID =  '" + contentItemID + "'" +
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
    public static ArrayList<ContentItem> getContentItems() {
        String selectStmt = "SELECT * FROM content-item";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<ContentItem> contentItems = new ArrayList<>();
            while (rsEmp.next()) {
                ContentItem returningContentItem = new ContentItem();
                returningContentItem.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItem.setDate(Date.valueOf(rsEmp.getString("date")));
                returningContentItem.setStatus(rsEmp.getString("status"));
                returningContentItem.setCourseName(rsEmp.getString("courseName"));
                returningContentItem.setModuleID(rsEmp.getString("moduleID") == null ? null : rsEmp.getInt("moduleID"));
                returningContentItem.setWebcastID(rsEmp.getString("webcastID") == null ? null : rsEmp.getInt("webcastID"));
                contentItems.add(returningContentItem);
            }
            return contentItems;
        } catch (SQLException e) {
            System.out.println("While selecting all content-items, an error occurred: " + e);
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

    public static ArrayList<ContentItem> getContentItemsByCourse(String courseName) {
        String selectStmt = "SELECT * FROM [content-item] WHERE courseName = '" + courseName + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<ContentItem> contentItems = new ArrayList<>();
            while (rsEmp.next()) {
                ContentItem returningContentItem = new ContentItem();
                returningContentItem.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItem.setDate(Date.valueOf(rsEmp.getString("date")));
                returningContentItem.setStatus(rsEmp.getString("status"));
                returningContentItem.setCourseName(rsEmp.getString("courseName"));
                returningContentItem.setModuleID(rsEmp.getString("moduleID") == null ? null : rsEmp.getInt("moduleID"));
                returningContentItem.setWebcastID(rsEmp.getString("webcastID") == null ? null : rsEmp.getInt("webcastID"));
                contentItems.add(returningContentItem);
            }
            return contentItems;
        } catch (SQLException e) {
            System.out.println("While selecting all content-items, an error occurred: " + e);
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
    public static ContentItem getContentItemsByID(int ID) {
        String selectStmt = "SELECT * FROM [content-item] WHERE contentItemID = '" + ID + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            while (rsEmp.next()) {
                ContentItem returningContentItem = new ContentItem();
                returningContentItem.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItem.setDate(Date.valueOf(rsEmp.getString("date")));
                returningContentItem.setStatus(rsEmp.getString("status"));
                returningContentItem.setCourseName(rsEmp.getString("courseName"));
                returningContentItem.setModuleID(rsEmp.getString("moduleID") == null ? null : rsEmp.getInt("moduleID"));
                returningContentItem.setWebcastID(rsEmp.getString("webcastID") == null ? null : rsEmp.getInt("webcastID"));
                return returningContentItem;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("While selecting all content-items, an error occurred: " + e);
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


    public static boolean createContentItem(String date, String status, String courseName, int moduleID, int webcastID) {
        SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
        Date date2 = Date.valueOf(date);
        String updateStmt =
                "BEGIN\n" + "INSERT INTO [content-item] VALUES('"+ date2 + "','"+status+"','"+ courseName +"','"+ moduleID + "','"+ webcastID +"')\n"+
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
    public static Boolean updateContentItem(int contentItemID, String date, String status, String courseName, int moduleID, int webcastID) {
        SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
        Date date2 = Date.valueOf(date);
        String updateStmt =
                "BEGIN\n" + "UPDATE [content-item] set date = '"+date2+"',status = '"+status+"',courseName = '"+courseName+"',moduleID = '"+moduleID+"',webcastID = '"+webcastID+"' WHERE contentItemID =  '" + contentItemID +  "'" +
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
