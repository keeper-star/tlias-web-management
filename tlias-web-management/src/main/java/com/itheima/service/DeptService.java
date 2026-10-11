package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询所有部门数据
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门数据
     */
    void deleteById(Integer id);

    /**
     * 添加部门数据
     */
    void add(Dept dept);

    /**
     * 根据id查询部门数据
     */
    Dept getById(Integer id);

    /**
     * 修改部门数据
     */
    void update(Dept dept);
}
