package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


/**
 * 员工管理Controller
 */
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工信息
     */
//    @GetMapping("/emps")  //default value 用来指定默认值
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       @RequestParam String name, @RequestParam Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//
//        log.info("分页查询：{} {} {} {} {} {}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    @GetMapping("/emps")  //default value 用来指定默认值
    public Result page(EmpQueryParam empQueryParam) {

        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);

    }

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();

    }

    /**
     * 删除员工
     */
//    @DeleteMapping("/emps")
//    public Result delete(Integer[] ids){
//
//        log.info("删除员工：{}", Arrays.toString(ids));
//        return Result.success();
//    }

    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids){

        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();

    }
    /**
     * 根据id查询员工
     */
    @GetMapping("/emps/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     * @param emp
     * @return
     */
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/emps/list")
    public Result list(){
        log.info("查询所有员工");
        List<Emp> empList = empService.getlist();
        return Result.success(empList);
    }
}
