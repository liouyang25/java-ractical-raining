package dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import dao.IDataAccess;
import model.OrderDetail;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-15 12:59
 * @Description:
 */
public class IDataAccessImpl implements IDataAccess {
    @Override
    public <T> List<T> getList(Class<T> clazz) {
        String clsName = clazz.getSimpleName().toLowerCase();
        String sql = null;
        switch (clsName) {
            case "orderdetail":
                sql = "select * from order_detail";
                break;
            case "goods":
                sql = "select * from goods";
                break;
            case "order":
                sql = "select * from orders";
                break;
            case "user":
                sql = "select * from  user";
                break;
            default:
                break;
        }
        return new ObjectUtil<OrderDetail>().getList(sql, clazz);
    }

    @Override
    public <T> T getEntityById(Class<T> clazz, String id) {
        String sql = null;
        String clsName = clazz.getSimpleName().toLowerCase();
        switch (clsName) {
            case "orderdetail":
                sql = "select * from order_detail where id = ?";
                break;
            case "goods":
                sql = "select * from goods where id = ?";
                break;
            case "order":
                sql = "select * from orders where id = ?";
                break;
            case "user":
                sql = "select * from  user where id = ?";
                break;
            default:
                break;
        }
        return  new ObjectUtil<T>().getOne(sql, clazz, id);
    }
}
