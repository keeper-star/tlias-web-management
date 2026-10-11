package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//
//        //1. 调用mapper接口，获取总记录数
//        long total = empMapper.count();
//
//        //2. 调用mapper接口，获取当前页数据
//        List<Emp> rows = empMapper.list((page-1)*pageSize, pageSize);
//
//        //3.封装PageResult对象并返回
//        return new PageResult<Emp>(total, rows);
//
//    }

    /**
     * PageHelper分页查询
     *
     * @param empQueryParam
     * @return
     */

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> rows = empMapper.list(empQueryParam);

        //3.解析查询结果,并封装
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    @Transactional//(rollbackFor = {Exception.class})  // !!!!表示当前方法是一个事务方法，如果方法执行过程中出现异常，事务会回滚
    // 默认只有出现运行时异常RuntimeException事务才会回滚
    @Override
    public void save(Emp emp) {


        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //2.保存员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());

            });
            empExprMapper.insertBatch(exprList);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {

        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.批量删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);

    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }


    @Transactional//(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据ID修改员工的工作经历
        //2.1 先根据员工ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));  //用Arrays.asList将员工ID转换为List集合

        //2.2 再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> getlist() {
        return empMapper.getList();
    }

    @Override
    public LoginInfo login(Emp emp) {

        //1.调用mapper接口根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2.判断是否存在这个员工，如果存在，返回查询结果
        if (e != null) {

            log.info("登录成功,员工信息：{}", e);
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);

        }
        //3.不存在，返回null
        return null;
    }

}









