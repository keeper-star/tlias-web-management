package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

public interface StudentService {

    //增加学生信息
    void addStudent(Student student);

    //根据id批量删除学生信息
    void deleteByIds(Integer[] ids);

    //根据查询条件分页查询学生信息
    PageResult<Student> queryPage(StudentQueryParam queryParam);

    //根据id查询学生信息
    Student queryById(Integer id);

    //修改学生信息
    void updateStudent(Student student);

    //违纪处理
    void handleViolationById(Integer id, Integer score);

}
