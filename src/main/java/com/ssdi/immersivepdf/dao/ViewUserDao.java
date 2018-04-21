package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ViewUserDao {

    private ConnectionData connectionData;
    public Books getAllBooksForUser(User user) throws SQLException{
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "SELECT * FROM BOOKS b JOIN USER u on b.userid = u.userid and email=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            ResultSet res = pstmt.executeQuery();

            ArrayList<Book> userBookCollection = new ArrayList<Book>();
            while (res.next()){
                Book book = new Book();
                book.setBookid(res.getInt("bookid"));
                book.setBookname(res.getString("bookname"));
                book.setDescription(res.getString("description"));
                book.setUserid(res.getInt("userid"));
                book.setLocation(res.getString("location"));
                book.setIsfavorite(res.getBoolean("isfavorite"));
                book.setBookmark(res.getInt("bookmark"));
                userBookCollection.add(book);
            }
            Books allBooks = new Books();
            allBooks.setBookCollection(userBookCollection);
            return allBooks;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
