package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.util.DBConnector;

import java.sql.*;


public class RegisterUserDao {

    public int register(User user){
        try {
            Connection connection = DBConnector.getConnection();
            String sql = "INSERT INTO USER(firstname,lastname,email,role,password) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getPassword());
            pstmt.executeUpdate();
            return 200;
            /* connection.close(); */

        } catch (SQLException sqlError) {
            if (sqlError.getErrorCode() == 1062){
                return 401;
            }else{
                return 402;
            }
        }
    }
}
