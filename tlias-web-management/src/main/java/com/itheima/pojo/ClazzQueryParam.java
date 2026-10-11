package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClazzQueryParam {

    private Integer page=1; // 当前页码
    private Integer pageSize=10; // 每页大小
    private String name; // 班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开课时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结课时间

}
