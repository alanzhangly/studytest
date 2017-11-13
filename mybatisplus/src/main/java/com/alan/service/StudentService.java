package com.alan.service;

import com.alan.dao.StudentMapper;
import com.alan.pojo.Student;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ke Zhang on 2017/9/13.
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List getStudents(){
        List result = studentMapper.selectList(new EntityWrapper<Student>().like("stu_name","张%"));
        return result;
    }

    public Integer addStudent(String name,Integer age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return studentMapper.insert(student);
    }

    public List getListBySQL(){
        return studentMapper.selectListBySQL();
    }

}
