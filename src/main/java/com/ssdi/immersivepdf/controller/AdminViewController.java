package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.BookEntryDao;
import com.ssdi.immersivepdf.dao.GetBooksDao;
import com.ssdi.immersivepdf.dao.UserDataDao;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.admin.Users;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/admin/")
public class AdminViewController {

    @Autowired
    private UserDataDao userDataDao;
    @Autowired
    private GetBooksDao getbooksDao;
    @Autowired
    private BookEntryDao bookEntryDao;


    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response getAllUsers(@RequestBody User user){

        Response response = new Response();
        try{
            Users users = userDataDao.getAllUsers();
            response.setData(users);
            response.setStatusMessage("Successful");
            response.setStatusCode(200);
        }catch (SQLException e){
            response.setStatusCode(303);
            response.setStatusMessage("Error while fetching user details");
        }
        return response;
    }

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response viewUserBooks(@RequestBody User request) {

        Response response = new Response();
        try {
            Books userBookCollection = getbooksDao.getAllBooksForUser(request);
            response.setData(userBookCollection);
            response.setStatusMessage("Successful");
            response.setStatusCode(200);
        }catch (SQLException e){
            response.setStatusCode(303);
            response.setStatusMessage("Error while fetching books");
        }
        return response;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response deleteUser(@RequestBody User user){
        Response response = new Response();
        if (userDataDao.deleteUser(user)) {
            response.setStatusMessage("User Successfully Deleted.");
            response.setStatusCode(200);
        }else {
            response.setStatusMessage("Failed. User data doesn't exist.");
            response.setStatusCode(400);
        }
        return response;
    }

    @RequestMapping(value = "/grantPrivilege", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response grantPrivilege(@RequestBody User user){
        Response response = new Response();
        if (userDataDao.allowPasswordReset(user)) {
            response.setStatusMessage("Successfully updated the privilege");
            response.setStatusCode(200);
        }else {
            response.setStatusMessage("Failed to update");
            response.setStatusCode(400);
        }
        return response;
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response deleteBook(@RequestBody Book book){
        Response response = new Response();
        int result = bookEntryDao.deleteBook(book);
        response.setStatusCode(result);
        if (result == 200){
            response.setStatusMessage("Entry successfully deleted.");
        }else if(result == 400){
            response.setStatusMessage("Book does not exist.");
        }else {
            response.setStatusMessage("Error occurred while deleting the book entry");
        }
        return response;
    }
}
