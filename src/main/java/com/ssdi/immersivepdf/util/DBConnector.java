package com.ssdi.immersivepdf.util;

import com.ssdi.immersivepdf.config.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnector {

    private static Connection connection;

//    public static Connection getConnection() throws SQLException {
//        if (connection == null||connection.isClosed()){
//            try {
//                connection = DriverManager.
//                        getConnection("jdbc:mysql://" + "13.59.54.128" + ":" + 3306 + "/" + Constants.databaseEnvironmentProduction, "root", "syntel123$");
//            } catch (SQLException e) {
//                System.out.println("Connection Failed!:\n" + e.getMessage());
//            }
//
//            if (connection != null) {
//                System.out.println("SUCCESS!!!! You made it, take control of your database now!");
//            } else {
//                System.out.println("FAILURE! Failed to make connection!");
//            }
//            return connection;
//        }else {
//            return connection;
//        }
//    }

    public static Connection getConnection(IConnectionData data) throws SQLException{
        if(connection == null || connection.isClosed()){
            try{
                connection = DriverManager.getConnection("jdbc:mysql://" + "13.59.54.128" + ":" + 3306 + "/" + data.getUrl(), data.getUser(), data
                        .getPassword());
            }catch (SQLException e) {
                System.out.println("Connection Failed!:\n" + e.getMessage());
            }catch (Exception e){
                e.printStackTrace();
            }

            if (connection != null) {
                System.out.println("SUCCESS!!!! You made it, take control of your database now!");
            } else {
                System.out.println("FAILURE! Failed to make connection!");
            }
            return connection;
        }else {
            return connection;
        }
    }
}
