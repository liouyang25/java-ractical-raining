package model;

import model.common.Entity;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-13 09:57
 * @Description:
 */
public class OrderDetail extends Entity {
    private String orderId;
    private String goodsId;
    private String goodTitle;
    private int goodNum;
    private double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id='" + getId() + '\'' +
                ", createTime=" + getCreateTime() +
                ", deleteTime=" + getDeleteTime() +
                ", isDel='" + getIsDel() + '\'' +
                ", orderId='" + orderId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodTitle='" + goodTitle + '\'' +
                ", goodNum=" + goodNum +
                ", price=" + price +
                '}';
    }
}
