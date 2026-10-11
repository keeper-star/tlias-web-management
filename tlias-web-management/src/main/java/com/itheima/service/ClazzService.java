package com.itheima.service;

import com.itheima.pojo.*;

import java.util.List;

public interface ClazzService {

     /**
      * 添加班级
      * @param clazz
      */
     void add(Clazz clazz);

     /**
      * 删除班级
      * @param id
      */
     void deleteClazz(Integer id);

     /**
      * 根据条件查询班级列表 分页查询
      * @param clazzQueryParam
      */
     PageResult<Clazz> list(ClazzQueryParam clazzQueryParam);

     /**
      * 根据id查询班级
      * @param id
      */
     Clazz getById(Integer id);

     /**
      * 查询所有班级列表
      */
     List<Clazz> listAllClazz();

     /**
      * 修改班级
      * @param clazz
      */
     void updateClazz(Clazz clazz);



}
