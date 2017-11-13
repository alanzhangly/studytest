package com.alan.dao;

import com.alan.SuperMapper;
import com.alan.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Ke Zhang on 2017/9/13.
 */
@Mapper
public interface StudentMapper extends SuperMapper<Student> {

    @Select("select id, stu_name as name,age from student")
    List<Student> selectListBySQL();
}
