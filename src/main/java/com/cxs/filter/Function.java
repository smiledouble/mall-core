package com.cxs.filter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/29 20:11
 */
public class Function {
    /**
     * 种子
     */
    private int seed;
    /**
     * 长度
     */
    private int length;

    public Function(int seed, int length) {
        this.seed = seed;
        this.length = length;
    }

    /**
     * 获得缩影的方法
     *
     * @param object
     * @return
     */
    public int getPosition(Object object) {
        int hash = hashCode(object.toString());
        //做与运算，hash出不同的索引
        return hash & (length - 1);

    }

    /**
     * 定义hash算法
     *
     * @param valueStr
     * @return
     */
    public int hashCode(String valueStr) {
        //转成char类型
        char[] value = valueStr.toCharArray();
        int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;
            for (int i = 0; i < value.length; i++) {
                h = this.seed * h + val[i];
            }
        }
        return h;

    }
}
