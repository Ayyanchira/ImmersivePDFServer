package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.View;
import com.ssdi.immersivepdf.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewUserDao {
    public boolean view(View view){
        try {
            Connection connection = DBConnector.getConnection();
            String sql = "SELECT * FROM BOOKS WHERE userid = (SELECT userid from USER WHERE email=?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, view.getEmail());
            pstmt.executeUpdate();
            ResultSet res = pstmt.executeQuery();
            if (res.first()) {
                return true;
            }
            else{
                return false;
            }
            /* connection.close(); */

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
