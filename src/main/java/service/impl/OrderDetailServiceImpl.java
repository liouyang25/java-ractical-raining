package service.impl;

import dao.OrderDetailDAO;
import dao.impl.OrderDetailDAOImpl;
import service.OrderDetailService;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 10:36
 * @Description:
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailDAO orderDetailDAO;

    public OrderDetailServiceImpl() {
        this.orderDetailDAO = new OrderDetailDAOImpl();
    }
}
