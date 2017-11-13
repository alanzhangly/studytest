package com.alan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ke Zhang on 2017/10/25.
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"/login","/login.html"})
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login.json")
    @ResponseBody
    public String login(@RequestParam String loginName, @RequestParam String password){
        return "1";
    }
}
