package com.itheima.controller;

import com.itheima.pojo.Dept;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.itheima.service.DeptService;
import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RequestMapping("/depts")  可以将请求映射到类上，表示该类中的所有方法的请求都以/depts为前缀
@RestController
public class DeptController {

     @Autowired
     private DeptService deptService;

    /**
     * 查询所有部门数据
     */
     @GetMapping("/depts")
     public Result list(){

         System.out.println("查询全部的部门数据");
         List<Dept> deptList = deptService.findAll();
         return Result.success(deptList);
     }

    /**
     * 删除部门数据 @RequestParam 表示接收请求参数
     * 注意事项：一旦声明了@RequestParam，就一定要传递参数，否则会报错（默认required是true） 如果不传递，可以设置required为false
     * 方式二：可以省略@RequestParam，但需要将参数名和方法参数名一致
     */
     @DeleteMapping("/depts")
     public Result delete(@RequestParam(value = "id",required = false) Integer deptId){

         System.out.println("根据ID删除部门:"+deptId);
         deptService.deleteById(deptId);
         return Result.success();
     }

    /**
     * 添加部门数据 @RequestBody 表示接收请求体中的数据 可以接收JSON数据
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){

        System.out.println("添加部门:"+dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门数据 @PathVariable 表示接收路径中的参数 可以省略@PathVariable，但需要将参数名和方法参数名一致
     */
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer id){

        System.out.println("根据ID查询部门:"+id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门数据 @RequestBody 表示接收请求体中的数据 可以接收JSON数据
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){

        System.out.println("修改部门:"+dept);
        deptService.update(dept);
        return Result.success();
    }

}
