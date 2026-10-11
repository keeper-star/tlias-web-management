package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class StudentController {

        @Autowired
        private StudentService studentService;

    /**
     * 增加学生信息
     */
    @PostMapping("/students")
    public Result addStudent(@RequestBody Student student){
        log.info("增加学生信息{}", student);
        studentService.addStudent(student);
        return Result.success();
    }
    /**
     * 根据id批量删除学生信息
     * @param ids
     */
    @DeleteMapping("/students/{ids}")
    public Result deleteById(@PathVariable("ids") Integer[] ids){
        log.info("删除学生，id：{}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }
    /**
     * 根据查询条件分页查询学生信息
     * @param queryParam
     */
    @GetMapping("/students")
     public Result queryPage(StudentQueryParam queryParam){
         log.info("学生分页查询{}", queryParam);
         PageResult<Student> result = studentService.queryPage(queryParam);
         return Result.success(result);
     }
    /**
     * 根据id查询学生信息
     */
    @GetMapping("/students/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        log.info("根据id查询学生信息{}", id);
        Student student = studentService.queryById(id);
        return Result.success(student);
    }
    /**
     * 修改学生信息
     */
    @PutMapping("/students")
    public Result updateStudent(@RequestBody Student student){
        log.info("修改学生信息{}", student);
        studentService.updateStudent(student);
        return Result.success();
    }
    /**
     * 违纪处理
     */
    @PutMapping("/students/violation/{id}/{score}")
    public Result handleViolationById(@PathVariable("id") Integer id, @PathVariable("score") Integer score){
        log.info("违纪处理{}{}", id, score);
        studentService.handleViolationById(id,score);
        return Result.success();
    }


}
