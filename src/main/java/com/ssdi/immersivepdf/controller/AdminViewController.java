package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.GetBooksDao;
import com.ssdi.immersivepdf.dao.UserDataDao;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class AdminViewController {

    private GetBooksDao getbooksDao;
    private UserDataDao userDataDao;



}
