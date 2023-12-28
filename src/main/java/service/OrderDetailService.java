package service;

import model.OrderDetail;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 09:30
 * @Description:
 */
public interface OrderDetailService {
    /**
     * 根据订单编号获取订单详细信息
     *
     * @param serialNumber
     * @return
     */
    List<OrderDetail> findOrderDetailBySN(String serialNumber);
}
