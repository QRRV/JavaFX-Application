package com.example.codecademy.Repository;

import com.example.codecademy.Domain.ContentItem;
import com.example.codecademy.Domain.ContentItemProgress;
import com.example.codecademy.Domain.ContentItemWatchCount;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContentItemProgressRepository {
    public static boolean deleteContentItemProgress(String contentItemID, String studentEmail){
        String updateStmt =
                "BEGIN\n" + "DELETE FROM contentItemProgress WHERE contentItemID =  '" + contentItemID + "'" + " AND emailAddress = '" + studentEmail + "'" +
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
    public static ArrayList<ContentItemProgress> getContentItemProgresses() {
        String selectStmt = "SELECT * FROM contentItemProgress";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<ContentItemProgress> contentItemProgresses = new ArrayList<>();
            while (rsEmp.next()) {
                ContentItemProgress returningContentItemProgress = new ContentItemProgress();
                returningContentItemProgress.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItemProgress.setEmailAddress(rsEmp.getString("emailAddress"));
                returningContentItemProgress.setPercentage(rsEmp.getInt("percentage"));
                contentItemProgresses.add(returningContentItemProgress);
            }
            return contentItemProgresses;
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

    public static ArrayList<ContentItemProgress> getContentItemProgressByContentItemID(int contentItemID) {
        String selectStmt = "SELECT * FROM contentItemProgress WHERE contentItemID = '" + contentItemID + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<ContentItemProgress> contentItemProgresses = new ArrayList<>();
            while (rsEmp.next()) {
                ContentItemProgress returningContentItemProgress = new ContentItemProgress();
                returningContentItemProgress.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItemProgress.setEmailAddress(rsEmp.getString("emailAddress"));
                returningContentItemProgress.setPercentage(rsEmp.getInt("percentage"));
                contentItemProgresses.add(returningContentItemProgress);
            }
            return contentItemProgresses;
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
    public static ArrayList<ContentItemWatchCount> getContentItemsWatchCount() {
        String selectStmt = "SELECT contentItemID, COUNT(*) as count FROM ContentItemProgress " +
                "GROUP BY contentItemID ORDER BY count DESC ";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ArrayList<ContentItemWatchCount> contentItemWatchCounts = new ArrayList<>();
            while (rsEmp.next()) {
                ContentItemWatchCount returningContentItemWatchCount = new ContentItemWatchCount();
                returningContentItemWatchCount.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItemWatchCount.setWatchCount(rsEmp.getInt("count"));
                contentItemWatchCounts.add(returningContentItemWatchCount);
            }
            return contentItemWatchCounts;
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
    public static ContentItemProgress getContentItemProgressByContentItemIDAndStudentEmail(int contentItemID, String studentEmail) {
        String selectStmt = "SELECT * FROM contentItemProgress WHERE contentItemID = '" + contentItemID + "' AND emailAddress = '" + studentEmail + "'" ;
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DbUtil.dbExecuteQuery(selectStmt);
            ContentItemProgress contentItemProgresses = null;
            while (rsEmp.next()) {
                ContentItemProgress returningContentItemProgress = new ContentItemProgress();
                returningContentItemProgress.setContentItemID(rsEmp.getInt("contentItemID"));
                returningContentItemProgress.setEmailAddress(rsEmp.getString("emailAddress"));
                returningContentItemProgress.setPercentage(rsEmp.getInt("percentage"));
                contentItemProgresses = returningContentItemProgress;
            }
            return contentItemProgresses;
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

    public static boolean createContentItemProgress(int contentItemID,String studentEmail, int percentage) {
        String updateStmt =
                "BEGIN\n" + "INSERT INTO contentItemProgress VALUES('"+ contentItemID + "','"+studentEmail+"','"+ percentage + "')\n"+
                        "END;";
        //Execute DELETE operation
        try {
            DbUtil.dbExecuteUpdate(updateStmt);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while CREATE Operation: " + e);
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
    public static Boolean updateContentItemProgress(int contentItemID,String studentEmail, int percentage) {
        String updateStmt =
                "BEGIN\n" + "UPDATE contentItemProgress set contentItemID = '"+contentItemID+"',emailAddress = '"+studentEmail+"',percentage = '"+percentage+"' WHERE contentItemID =  '" + contentItemID + "'" + " AND emailAddress = '" + studentEmail + "' " +
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
