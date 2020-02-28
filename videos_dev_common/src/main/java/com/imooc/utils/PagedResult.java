package com.imooc.utils;

import java.util.List;

/**
 * @Description: 分页后的数据格式
 */
public class PagedResult<T> {

    private int page;           //当前页数
    private int total;          //总页数
    private long records;       //总记录数
    private List<T> rows;       //每行显示的内容

    public PagedResult(int page, int total, long records, List<T> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
