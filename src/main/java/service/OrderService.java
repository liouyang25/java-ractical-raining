package service;

import model.Goods;
import model.Order;
import model.User;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 09:25
 * @Description:
 */
public interface OrderService {
    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 获取当前登录用户订单
     *
     * @param loginUser
     * @return
     */
    List<Order> findOrderByUser(User loginUser);

    /**
     * 修改订单信息
     *
     * @param id
     * @param order
     * @return
     */
    int updateOrderById(String id, Order order);

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    int deleteOrder(String id);
    int deleteOrderBySN(String id);
}
