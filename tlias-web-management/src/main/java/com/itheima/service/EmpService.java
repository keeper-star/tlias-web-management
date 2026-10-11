package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {

    /**
     * 分页查询员工信息
     * @param page 当前页
     * @param pageSize 每页大小
     */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 分页查询员工信息
     * @param empQueryParam
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工
     *
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 查询所有员工
     * @return
     */
    List<Emp> getlist();

    /**
     * 登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
