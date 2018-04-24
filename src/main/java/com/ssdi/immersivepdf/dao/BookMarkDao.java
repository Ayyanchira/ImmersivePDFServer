package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Service
public class BookMarkDao {
    private ConnectionData connectionData;
    public int bookMark(Book requestedBook){
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "UPDATE BOOKS SET bookmark=? WHERE bookid=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requestedBook.getBookmark());
            pstmt.setInt(2,requestedBook.getBookid());
            pstmt.executeUpdate();
            return 200;
        }
        catch (SQLException sqlError) {
            return 400;
        }
    }
}

