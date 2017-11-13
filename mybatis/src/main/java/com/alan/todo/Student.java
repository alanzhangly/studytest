package com.alan.todo;

/**
 * Created by Ke Zhang on 2017/9/22.
 */
public class Student {
    private String id;
    private String stu_name;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
