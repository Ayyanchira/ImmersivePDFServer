package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class LoginUserDao {

    private ConnectionData connectionData;
    public User login(Login login) throws SQLException{
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "SELECT * FROM USER WHERE email=? AND password=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login.getEmail());
            pstmt.setString(2, login.getPassword());
            ResultSet res = pstmt.executeQuery();
            User user = new User();
            boolean userExists = false;
            while (res.next()) {
                userExists = true;
                user.setEmail(res.getString("email"));
                user.setFirstname(res.getString("firstname"));
                user.setLastname(res.getString("lastname"));
                user.setRole(res.getString("role"));
                user.setAllowedToResetPassword(res.getBoolean("isAllowedToResetPwd"));
            }
            if (!userExists){
                throw new SQLException();
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User isValidUser(Login login) throws SQLException {
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "SELECT * FROM USER WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login.getEmail());
            ResultSet res = pstmt.executeQuery();
            User user = new User();
            boolean userExists = false;
            while (res.next()) {
                userExists = true;
                user.setEmail(res.getString("email"));
                user.setFirstname(res.getString("firstname"));
                user.setLastname(res.getString("lastname"));
                user.setRole(res.getString("role"));
                user.setAllowedToResetPassword(res.getBoolean("isAllowedToResetPwd"));
            }
            if (!userExists) {
                throw new SQLException();
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean resetPassword(Login login) {
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "UPDATE USER SET password = ? WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,login.getPassword());
            pstmt.setString(2,login.getEmail());
            int isPasswordUpdated = pstmt.executeUpdate();
            System.out.println("Password update status from DAO is : "+isPasswordUpdated);
            if (isPasswordUpdated != 0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
