package dao;

import model.Order;
import model.User;

import java.util.List;

/**
 * @author li
 */
public interface OrderDAO {
    /**
     * 根据订单号获取订单信息
     *
     * @param serialNumber
     * @return
     */
    Order selectOrderBySN(String serialNumber);

    /**
     * 根据用户姓名获取订单信息
     *
     * @param loginUser
     * @return
     */
    List<Order> selectOrderByUser(User loginUser);

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int insertOrder(Order order);


    /**
     * 根据ID更新订单信息
     *
     * @param id
     * @param order
     * @return
     */
    int updateOrderById(String id, Order order);

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    int deleteOrder(String id);
    int deleteOrderBySN(String id);

    Order getOrderById(String id);

    List<Order> selectOrder();
}
