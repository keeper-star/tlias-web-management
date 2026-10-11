package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 增加学生信息
     */
    @Override
    public void addStudent(Student student) {
        studentMapper.add(student);
    }
    /**
     * 根据id批量删除学生信息
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        studentMapper.delete(ids);
    }
    /**
     * 根据查询条件分页查询学生信息
     */
    @Override
    public PageResult<Student> queryPage(StudentQueryParam queryParam) {

        PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Student> students = studentMapper.findStudent(queryParam);
        Page<Student> page = (Page<Student>) students;
        return new PageResult<>(page.getTotal(), page.getResult());
    }
    /**
     * 根据id查询学生信息
     */
    @Override
    public Student queryById(Integer id) {
        return studentMapper.query(id);
    }
    /**
     * 修改学生信息
     */
    @Override
    public void updateStudent(Student student) {
        studentMapper.update(student);
    }
    /**
     * 违纪处理
     */
    @Override
    public void handleViolationById(Integer id, Integer score) {
        studentMapper.handleViolation(id, score);
    }


}
