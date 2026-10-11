package com.itheima.mapper;


import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //增加学生信息
    void add(Student student);

    //根据id批量删除学生信息
    void delete(Integer[] ids);

    //根据查询条件分页查询学生信息
    List<Student> findStudent(StudentQueryParam queryParam);

    //根据id查询学生信息
    Student query(Integer id);

    //修改学生信息
    void update(Student student);

    //根据id进行违纪处理
    void handleViolation(Integer id, Integer score);

    //获取学生学历数据
    List<Map<String, Object>> getStudentDegreeData();
}
