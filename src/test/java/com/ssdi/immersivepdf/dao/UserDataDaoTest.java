package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.admin.Users;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserDataDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    User user;
    ArrayList<User> userCollection;
    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);

        String sql;
        PreparedStatement pstmt;
        try {
            connection = DBConnector.getConnection(connectionData);

            //Deleting the user and collection of books from database.
            sql = "SELECT * from USER";
            pstmt = connection.prepareStatement(sql);
            ResultSet resultset = pstmt.executeQuery();
            userCollection = new ArrayList<>();
            while (resultset.next()){
                User user = new User();
                user.setEmail(resultset.getString("email"));
                user.setFirstname(resultset.getString("firstname"));
                user.setLastname(resultset.getString("lastname"));
                user.setRole("User");
                user.setAllowedToResetPassword(resultset.getBoolean("isAllowedToResetPwd"));
                userCollection.add(user);
            }
            Users allusers = new Users();
            allusers.setUsers(userCollection);


            //Deleting the user and collection of books from database.
            sql = "DELETE FROM USER WHERE email = 'robertdowney@gmail.com'";
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

            //Inserting new user
            sql= "INSERT INTO USER(firstname,lastname,email,role,password) VALUES('Robert','Downey','robertdowney@gmail.com','User','password')";
            PreparedStatement pstmt2 = connection.prepareStatement(sql);
            pstmt2.executeUpdate();
            user = new User();
            user.setEmail("robertdowney@gmail.com");
            user.setAllowedToResetPassword(false);
        }catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllUsers() {
        UserDataDao userDataDao = new UserDataDao();
        try{
            Users users = userDataDao.getAllUsers();
            assertTrue(users.getUsers().size() == userCollection.size());
            User user = users.getUsers().get(0);
            System.out.println(user.getEmail() + " and from the collection " + userCollection.get(0).getEmail());
            String emailFromDao = user.getEmail();
            String emailFromTestSetup = userCollection.get(0).getEmail();

            if (emailFromDao.equals(emailFromTestSetup)){
                assertTrue(true);
            }else {
                assertFalse(true);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void deleteUserWithExistingCredential() {
        UserDataDao userDataDao = new UserDataDao();
        assertTrue(userDataDao.deleteUser(user) == true);
    }

    @Test
    public void deleteNonExistingUser() {
        UserDataDao userDataDao = new UserDataDao();
        user.setEmail("strangeNonExistingemail@gmail.com");
        assertTrue(userDataDao.deleteUser(user) == false);
    }


    @Test
    public void allowPasswordReset() {
        UserDataDao userDataDao = new UserDataDao();
        assertTrue(userDataDao.allowPasswordReset(user) == true);
    }
}