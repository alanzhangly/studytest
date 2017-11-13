package com.alan.controller;

import com.alan.service.StudentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ke Zhang on 2017/9/13.
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/get/students",method = RequestMethod.GET)
    public Object getStudent(){
        return studentService.getStudents().toString();
//        return studentService.getListBySQL().toString();
    }

    @RequestMapping(value = "/add/student")
    public Integer addStudent(@RequestParam String name,@RequestParam Integer age){
        return studentService.addStudent(name,age);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
