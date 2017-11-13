package com.alanzhang.controller;

import com.alanzhang.dao.TUserMapper;
import com.alanzhang.service.TUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ke Zhang on 2017/11/2.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TUserServiceImpl tUserService;

    @ResponseBody
    @RequestMapping("/add")
    public void add(){
        tUserService.insertUser();
    }
}
