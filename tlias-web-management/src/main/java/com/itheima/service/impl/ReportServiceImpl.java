package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public JobOption getEmpJobData() {

        //1.调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); //map: pos=教研主管，num=1 String指的是pos和num的键
                                                                        //Object指的是pos和num的值

        //2.组装结果并返回
        //因为list里面没有map方法，所以需要先调用stream()方法，再调用map()方法遍历拿到dataMap指定的数据，最后调用toList()方法将结果转换为List
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {

        //1.调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpGenderData();

        //2.组装结果并返回
        return list;
    }

    @Override
    public ClazzOption getClazzData() {

        List<Map<String, Object>> list = clazzMapper.getClazzData();   //储存为键值对类型

        //2.组装结果并返回
        //因为list里面没有map方法，所以需要先调用stream()方法，再调用map()方法遍历拿到dataMap指定的数据，最后调用toList()方法将结果转换为List
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzOption(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {

        List<Map<String, Object>> list = studentMapper.getStudentDegreeData();
        return list;
    }
}