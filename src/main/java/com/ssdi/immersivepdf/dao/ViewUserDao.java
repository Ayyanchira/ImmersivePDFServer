package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.View.View;
import com.ssdi.immersivepdf.util.ConnectionData;
import com.ssdi.immersivepdf.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewUserDao {

    private ConnectionData connectionData;
    public Books view(View view){
        connectionData = new ConnectionData();
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "SELECT * FROM BOOKS WHERE userid = (SELECT userid from USER WHERE email=?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, view.getEmail());
            //pstmt.executeUpdate();
            ResultSet res = pstmt.executeQuery();

            ArrayList<Book> userBookCollection = new ArrayList<Book>();
            //for all the books
            while (res.next()){

                Book book = new Book();
                book.setBookid(res.getInt("bookid"));
                book.setBookname(res.getString("bookname"));
                book.setDescription(res.getString("description"));
                book.setUserid(res.getInt("userid"));
                book.setLocation(res.getString("location"));
                book.setBookmark(res.getInt("bookmark"));

                userBookCollection.add(book);

                //create a book object
                //store it in a arraylist of type Books
                // and return Books.
            }
            Books allBooks = new Books();
            allBooks.setBookCollection(userBookCollection);
            return allBooks;
//            if (res.first()) {
//                return true;
//            }
//            else{
//                return false;
//            }
            /* connection.close(); */

        } catch (SQLException e) {
            e.printStackTrace();
            return new Books();
        }
    }
}
