package service.impl;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailDAOImpl;
import model.OrderDetail;
import service.OrderDetailService;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 10:36
 * @Description:
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailDAO orderDetailDAO;
    private OrderDAO orderDAO;

    public OrderDetailServiceImpl() {
        orderDetailDAO = new OrderDetailDAOImpl();
        orderDAO = new OrderDAOImpl();
    }

    @Override
    public List<OrderDetail> findOrderDetailBySN(String serialNumber) {
//        return orderDAO.selectOrderBySN(serialNumber);

        //TODO
        return null;
    }
}
