package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.GetBooksDao;
import com.ssdi.immersivepdf.dao.UserDataDao;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.admin.Users;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/admin/")
public class AdminViewController {

    private GetBooksDao getbooksDao;
    private UserDataDao userDataDao;

    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response getAllUsers(@RequestBody User user){

        Response response = new Response();
        try{
            Users users = userDataDao.getAllBooksForUser();
            response.setData(users);
            response.setStatusMessage("Successful");
            response.setStatusCode(200);
        }catch (SQLException e){
            response.setStatusCode(303);
            response.setStatusMessage("Error while fetching user details");
        }
        return response;
    }

}
