package com.cxs.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 15:58
 */
public class PageBean<T> implements Serializable {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 当前页数据
     */
    private List<T> data;
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页多少条
     */
    private int size;

    public PageBean() {
    }

    public PageBean(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public PageBean(Long total, List<T> data, int page, int size) {
        this.total = total;
        this.data = data;
        this.page = page;
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
