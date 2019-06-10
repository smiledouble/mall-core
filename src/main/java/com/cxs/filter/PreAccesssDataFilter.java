package com.cxs.filter;

import com.cxs.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/29 13:57
 * 访问数据库之前的过滤验证 防止缓存雪崩
 * 使用redis实现布隆去重算法
 */
@Component
public class PreAccesssDataFilter {


    @Autowired
    private RedisClient redis;
    /**
     * 利用四个hash函数 算出该对象在位图中的索引
     */
    public static final int LABEL_NUM = 4;
    /**
     * 在使用redis时候 默认使用int类型的最大范围作为位图的大小
     */
    public static final int BIT_MAX_SIZE = (2 << 30) - 1;
    /**
     * 初始化函数大小
     */
    public static Function[] fun = new Function[LABEL_NUM];
    /**
     * 用四个质数来做下面的运算
     */
    public static int[] seeds = new int[]{11, 13, 17, 19};
    /**
     * 给定bitmap的key
     */
    public static final String BLOOM_FILTER_KEY = "BLOOM_FILTER_BIT_KEY";

    /**
     * 提供构造方法
     */
    public PreAccesssDataFilter() {
        for (int i = 0; i < fun.length; i++) {
            fun[i] = new Function(seeds[i], BIT_MAX_SIZE);
        }
    }

    /**
     * 将值存到位图里面去
     *
     * @param object
     */
    public void setValue(Object object) {
        //首先得到特征索引的位置
        for (int i = 0; i < fun.length; i++) {
            int position = fun[i].getPosition(object);
            System.out.println(position);
            redis.set(BLOOM_FILTER_KEY, position, true);
        }
        System.out.println("\n");
    }

    /**
     * 判断是否存在
     *
     * @param object
     * @return
     */
    public boolean isExist(Object object) {
        for (int i = 0; i < fun.length; i++) {
            int position = fun[i].getPosition(object);
            boolean b = redis.get(BLOOM_FILTER_KEY, position);
            if (!b) {
                return false;
            }
        }
        return true;

    }


}
