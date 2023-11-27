package model;

import model.common.Entity;

import java.util.Date;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 08:47
 * @Description:
 */
public class Goods extends Entity {
    private String name;
    private double price;
    private int number;
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "id='" + getId() + '\'' +
                ", createTime=" + getCreateTime() +
                ", deleteTime=" + getDeleteTime() +
                ", isDel='" + getIsDel() + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", brand='" + brand + '\'' +
                '}';
    }
}
