package service.impl;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.Order;
import model.User;
import service.OrderService;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 10:49
 * @Description:
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    public OrderServiceImpl() {
        orderDAO = new OrderDAOImpl();
    }

    @Override
    public int addOrder(Order order) {
        return orderDAO.insertOrder(order);
    }

    @Override
    public List<Order> findOrderByUser(User loginUser) {
        return orderDAO.selectOrderByUser(loginUser);
    }

    @Override
    public int updateOrderById(String id, Order order) {
        return orderDAO.updateOrderById(id, order);
    }

    @Override
    public int deleteOrder(String id) {
        return orderDAO.deleteOrder(id);
    }
    @Override
    public int deleteOrderBySN(String id) {
        return orderDAO.deleteOrderBySN(id);
    }
}
