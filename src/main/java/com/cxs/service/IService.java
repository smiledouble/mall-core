package com.cxs.service;

import com.cxs.model.PageBean;

import java.io.Serializable;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 16:00
 */
public interface IService<T> {

    /**
     * 添加一个实体
     *
     * @param t
     * @return
     */
    T add(T t);

    /**
     * 根据id删除一个实体
     *
     * @param id
     * @return
     */
    int delete(Serializable id);

    /**
     * 修改一个实体
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 查询一个实体
     *
     * @param id
     * @return
     */
    T find(Serializable id);

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    PageBean<T> findAll(int page, int size);
}
