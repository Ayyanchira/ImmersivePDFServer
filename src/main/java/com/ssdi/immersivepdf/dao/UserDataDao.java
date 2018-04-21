package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.admin.Users;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class UserDataDao {

    private ConnectionData connectionData;
    public Users getAllUsers() throws SQLException {
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "SELECT * FROM USER WHERE role = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "User");
            ResultSet res = pstmt.executeQuery();

            ArrayList<User> userCollection = new ArrayList<>();
            while (res.next()){
                User user = new User();
                user.setEmail(res.getString("email"));
                user.setFirstname(res.getString("firstname"));
                user.setLastname(res.getString("lastname"));
                user.setRole("User");
                user.setAllowedToResetPassword(res.getBoolean("isAllowedToResetPwd"));
                userCollection.add(user);
            }
            Users allusers = new Users();
            allusers.setUsers(userCollection);
            return allusers;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
