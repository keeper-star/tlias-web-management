package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 添加班级
     * @param clazz
     */
    @Override
    public void add(Clazz clazz) {
        log.info("添加班级:{}", clazz);
        clazzMapper.insertClazz(clazz);
    }

    /**
     * 删除班级
     * @param id
     */
    @Override
    public void deleteClazz(Integer id) {
        log.info("删除班级:{}", id);
        clazzMapper.delete(id);
    }

    /**
     * 根据条件查询班级列表 分页查询
     * @param clazzQueryParam
     */
    @Override
    public PageResult<Clazz> list(ClazzQueryParam clazzQueryParam) {

        log.info("根据条件查询班级列表:{}", clazzQueryParam);
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> rows = clazzMapper.list(clazzQueryParam);
        Page<Clazz> pageResult = (Page<Clazz>) rows;
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    /** 根据id查询班级
     * @param id
     */
    @Override
    public Clazz getById(Integer id) {
        log.info("查询班级:{}", id);
        return clazzMapper.get(id);

    }

    /**
     * 查询所有班级列表
     * @return
     */
    @Override
    public List<Clazz> listAllClazz() {
        log.info("查询所有班级列表");
        return clazzMapper.listAllClazz();
    }

    /**
     * 修改班级
     * @param clazz
     */
    @Override
    public void updateClazz(Clazz clazz) {
        log.info("修改班级:{}", clazz);

        clazzMapper.update(clazz);
    }

}
