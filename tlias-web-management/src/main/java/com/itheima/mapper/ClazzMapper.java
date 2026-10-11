package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    // 添加班级
    void insertClazz(Clazz clazz);

    // 删除班级
    void delete(Integer id);

    // 根据条件查询班级列表 分页查询
    List<Clazz> list(ClazzQueryParam clazzQueryParam);      // 查询所有班级数据

    // 根据id查询班级
    Clazz get(Integer id);

    //查询所有班级列表
    List<Clazz> listAllClazz();

    // 修改班级
    void update(Clazz clazz);

    List<Map<String, Object>> getClazzData();
}
