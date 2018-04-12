package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class BookEntryDao {
    private ConnectionData connectionData;

    public int enterNewBook(String fileName, String filePath, String description, String email){
        connectionData = new ConnectionData();
        try {
            PreparedStatement pstmt;
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "SELECT * from USER WHERE email = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,email);
            ResultSet res = pstmt.executeQuery();
            int userid = 0;
            while (res.next()){
                userid = res.getInt("userid");
            }

            sql = "INSERT INTO BOOKS(bookname,description,userid,location) VALUES(?,?,?,?) ";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.setString(2, description);
            pstmt.setInt(3, userid);
            pstmt.setString(4, filePath);
            pstmt.executeUpdate();
            System.out.println("Successfully Uploaded!!!");

            return 200;
            /* connection.close(); */

        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
           return 401;
        }
    }
}
