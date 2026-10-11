package com.itheima.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
/**
 * 分页结果封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


}
