package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class FavUserDao {

    private ConnectionData connectionData;
    public int markfav(Book requestedBook){
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "UPDATE BOOKS SET isfavorite=? WHERE bookid=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, requestedBook.getIsfavorite());
            pstmt.setInt(2,requestedBook.getBookid());
            int sqlResult = pstmt.executeUpdate();
            if (sqlResult == 1){
                return 200;
            }else {
                return 301;
            }
            /* connection.close(); */
        }
        catch (SQLException sqlError) {
            return 400;
        }
    }
}
