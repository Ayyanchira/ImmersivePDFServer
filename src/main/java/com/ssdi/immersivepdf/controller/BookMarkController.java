package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.BookMarkDao;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@RestController
public class BookMarkController {
    @Autowired
    private BookMarkDao bookMarkDao;

    @RequestMapping(value = "/bookmark", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000")
    public Response markUser(@RequestBody Book book) {

        Response response = new Response();
        int result = bookMarkDao.bookMark(book);
        response.setStatusCode(result);
        if (result == 200) {
                response.setStatusMessage("Page book marked successfully");
        }
        else {
            response.setStatusMessage("Failed to book mark the page");
        }
        return response;
    }
}
