package dao;

import model.OrderDetail;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-13 10:01
 * @Description:
 */
public interface OrderDetailDAO {
    /**
     * 根据GoodsId查看订单详情
     *
     * @param goodId
     * @return
     */
    List<OrderDetail> selectOrderDetailByGoodId(String goodId);

    /**
     * 根据OrderId查看订单详情
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> selectOrderDetailByOrderId(String orderId);

    /**
     * 添加订单详情
     *
     * @param orderDetail
     * @return
     */
    int insertOrderDetail(OrderDetail orderDetail);

    /**
     * 更新订单信息
     *
     * @param id
     * @param orderDetail
     * @return
     */
    int updateOrderDetail(String id, OrderDetail orderDetail);

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    int deleteOrderDetail(String id);
}
