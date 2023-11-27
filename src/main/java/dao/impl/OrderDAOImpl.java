package dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import dao.OrderDAO;
import model.Order;
import model.User;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-13 09:41
 * @Description:
 */
public class OrderDAOImpl implements OrderDAO {
    @Override
    public Order selectOrderBySN(String serialNumber) {
        String sql = "select * from orders where serialNumber = ?";
        return new ObjectUtil<Order>().getOne(sql, Order.class, serialNumber);
    }

    @Override
    public List<Order> selectOrderByUser(User loginUser) {
        String sql = "select * from orders where consignee like ?";
        return new ObjectUtil<Order>().getList(sql, Order.class, "%" + loginUser.getUsername() + "%");
    }

    @Override
    public int updateOrderById(String id, Order order) {
        String sql = "update orders set serialNumber=?,isDel=?,createTime=?,deleteTime=?,user_id=?,consignee=?,consigneeAddress=?,phone=?,amount=?,state=? WHERE id = ?";
        return new ObjectUtil<Order>().update(sql, order.getSerialNumber(), order.getIsDel(), order.getCreateTime(), order.getDeleteTime(), order.getUserId(), order.getConsignee(),
                order.getConsigneeAddress(), order.getPhone(), order.getAmount(), order.getState(), id);
    }


    @Override
    public int insertOrder(Order order) {
        String sql = "insert into orders values(?,?,?,?,?,?,?,?,?,?,?)";
        return new ObjectUtil<Order>().add(sql, order.getId(), order.getSerialNumber(), order.getIsDel(), order.getCreateTime(), order.getDeleteTime(), order.getUserId(), order.getConsignee(),
                order.getConsigneeAddress(), order.getPhone(), order.getAmount(), order.getState());
    }

    @Override
    public int deleteOrder(String id) {
        String sql = "delete from orders where id=?";
        return new ObjectUtil<Order>().delete(sql, id);
    }
}
