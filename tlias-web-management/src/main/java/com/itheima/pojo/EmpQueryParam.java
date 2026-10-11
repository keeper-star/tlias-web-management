package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {

    private Integer page=1; // 当前页码
    private Integer pageSize=10; // 每页大小
    private String name; // 员工姓名
    private Integer gender; // 员工性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 入职开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 入职结束日期


}
