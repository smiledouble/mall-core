package com.cxs.service.impl;

import com.cxs.dao.IDao;
import com.cxs.model.PageBean;
import com.cxs.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 16:02
 */
public class AService<T> implements IService<T> {

    /**
     * 继承关系可见
     */
    protected IDao<T> dao;

    /**
     * 添加一个实体
     *
     * @param t
     * @return
     */
    @Override
    public T add(T t) {
        if (null == t) {
            throw new RuntimeException("添加的实体对象不能为空");
        }
        dao.insert(t);
        return t;
    }

    /**
     * 根据id删除一个实体
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Serializable id) {
        if (null == id) {
            //这里也可以加>0的判断，避免-1查询缓存一直未命中
            throw new RuntimeException("删除时Id不能为空");
        }

        return dao.deleteByPrimaryKey(id);
    }

    /**
     * 修改一个实体
     *
     * @param t
     * @return
     */
    @Override
    public int update(T t) {
        if (null == t) {
            throw new RuntimeException("修改的对象实体不能为空");
        }
        return dao.updateByPrimaryKeySelective(t);
    }

    /**
     * 查询一个实体
     *
     * @param id
     * @return
     */
    @Override
    public T find(Serializable id) {
        if (null == id) {
            //这里也可以加>0的判断，避免-1查询缓存一直未命中
            throw new RuntimeException("查询时Id不能为空");
        }
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageBean<T> findAll(int page, int size) {
        //连接sql语句的
        Page<Object> page1 = PageHelper.startPage(page, size);
        List<T> data = dao.selectByExample(null);
        long total = page1.getTotal();
        return new PageBean<T>(total, data, page, size);
    }
}
