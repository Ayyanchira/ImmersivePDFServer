package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.FavUserDao;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserFavController {
    @Autowired
    private FavUserDao favUserDao;

    @RequestMapping(value = "/markfav", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response favUser(@RequestBody Book book) {

        Response response = new Response();
        int result = favUserDao.markfav(book);
        response.setStatusCode(result);
        if (result == 200) {
            if (book.getIsfavorite()){
                response.setStatusMessage("Book marked as favorite");
            }
            else{
                response.setStatusMessage("Book unmarked from favorite list");
            }
        }
        else {
            response.setStatusMessage("Failed to mark book as favorite");
        }
        return response;
    }
}
