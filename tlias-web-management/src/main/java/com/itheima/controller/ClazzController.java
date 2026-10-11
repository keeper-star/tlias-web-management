package com.itheima.controller;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 添加班级
     * @param clazz
     */

    @PostMapping("/clazzs")
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级:{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 删除班级
     * @param id
     */
    @DeleteMapping("/clazzs/{id}")
    public Result deleteClazz(@PathVariable("id") Integer id) {
        log.info("删除班级:{}", id);
        clazzService.deleteClazz(id);

        return Result.success();
    }

    /**
     * 根据条件查询班级列表 分页查询
     * @param clazzQueryParam
     */
    @GetMapping("/clazzs")
    public Result list(ClazzQueryParam clazzQueryParam) {
        log.info("根据条件查询班级列表:{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.list(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询班级
     * @param id
     */
    @GetMapping("/clazzs/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("查询班级:{}", id);
        Clazz clazz = clazzService.getById(id);

        return Result.success(clazz);
    }
    /**
     * 查询所有班级列表
     */
    @GetMapping("/clazzs/list")
    public Result list()
    {
        log.info("查询所有班级列表");
        List<Clazz> clazzList = clazzService.listAllClazz();
        return Result.success(clazzList);
    }

    /**
     * 修改班级
     * @param clazz
     */
    @PutMapping("/clazzs")
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("修改班级:{}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }


}
