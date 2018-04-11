package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class BookEntryDao {
    private ConnectionData connectionData;

    public int enterNewBook(String fileName, String filePath, String description, String email){
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "INSERT INTO BOOKS(bookname,description,userid,location) VALUES(?,?,userid,?) SELECT userid FROM USER WHERE email=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.setString(2, description);
            pstmt.setString(3, filePath);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            return 200;
            /* connection.close(); */

        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
           return 401;
        }
    }
}
