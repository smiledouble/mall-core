package com.cxs.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 15:54
 */
public interface IDao<T> {

    /**
     * 插入一个实体
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 删除一个实体
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Serializable id);

    /**
     * 修改一个实体 不安全的
     *
     * @param t
     * @return
     */
    int updateByPrimaryKey(T t);

    /**
     * 修改一个实体，安全的 为null则不修改
     *
     * @param t
     * @return
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 根据id查询 一个实体
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Serializable id);

    /**
     * 全查询
     *
     * @param object
     * @return
     */
    List<T> selectByExample(Object object);
}
