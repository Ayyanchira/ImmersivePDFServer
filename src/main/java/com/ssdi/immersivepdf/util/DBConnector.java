package com.ssdi.immersivepdf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnector {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null||connection.isClosed()){
            try {
                connection = DriverManager.
                        getConnection("jdbc:mysql://" + "13.59.54.128" + ":" + 3306 + "/" + "`ImmersivePDF`", "root", "syntel123$");
            } catch (SQLException e) {
                System.out.println("Connection Failed!:\n" + e.getMessage());
            }

            if (connection != null) {
                System.out.println("SUCCESS!!!! You made it, take control     your database now!");
            } else {
                System.out.println("FAILURE! Failed to make connection!");
            }
            return connection;
        }else {
            return connection;
        }
    }

    public void connectToDB(){
        String host = "jdbc:mysql:13.59.54.128:3306";
        String username = "root";
        String password = "syntel123$";

        try (Connection connection = DriverManager.getConnection(host, username, password)) {
            System.out.println("Connection Successful");
        }catch (SQLException e){
            System.out.println("Some problem with database connection" + e.getMessage());
        }
    }

    public static Connection connectJDBCToAWSEC2() {

        System.out.println("----MySQL JDBC Connection Testing -------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + "13.59.54.128" + ":" + 3306 + "/" + "SSDI_Test", "root", "syntel123$");
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        if (connection != null) {
            System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        } else {
            System.out.println("FAILURE! Failed to make connection!");
        }
        return connection;
    }


    public static String runTestQuery(Connection conn) {
        Statement statement = null;
//        try {
//
//            System.out.println("Creating statement...");
//            statement = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM USER";
//            ResultSet rs = statement.executeQuery(sql);
//
//            //STEP 5: Extract data from result set
//            while (rs.next()) {
//                //Retrieve by column name
//                String name = rs.getString("firstname");
//                String bookcount = rs.getString("bookcount");
//
//                //Display values
//
//                returnString = returnString + "Name : "+name + " | Book Count : "+ bookcount +"\n";
//            }
//            //STEP 6: Clean-up environment
//            rs.close();
//            statement.close();
//            conn.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (statement != null)
//                    statement.close();
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
//        }//end try



    return "";
    }

}
