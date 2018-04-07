package com.ssdi.immersivepdf.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectorTest {

    @Test
    public void getConnection() {
        TestConnectionData connectionData = new TestConnectionData();
        System.out.println("----Testing DB Util -> getConnection()");
            try {
                Connection connection = DBConnector.getConnection(connectionData);
                assertTrue(connection != null);
                System.out.println("DB Connection util is working properly");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("DB Connection util failed in creating a connection");
            }
    }
}