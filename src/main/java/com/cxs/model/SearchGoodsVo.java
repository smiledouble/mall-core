package com.cxs.model;

import java.io.Serializable;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/29 16:46
 * 与前台对应的实体类对象
 */
public class SearchGoodsVo implements Serializable {

    private String id;
    private String goodsImg;
    private String goodsNameHl;
    private String goodsPrice;
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsNameHl() {
        return goodsNameHl;
    }

    public void setGoodsNameHl(String goodsNameHl) {
        this.goodsNameHl = goodsNameHl;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
