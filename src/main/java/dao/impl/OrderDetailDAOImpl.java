package dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import dao.OrderDetailDAO;
import model.OrderDetail;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-13 10:26
 * @Description:
 */
public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public List<OrderDetail> selectOrderDetailByGoodId(String goodId) {
        String sql = "select * from order_detail where goodsId = ?";
        return new ObjectUtil<List<OrderDetail>>().getList(sql, OrderDetail.class);
    }

    @Override
    public List<OrderDetail> selectOrderDetailByOrderId(String orderId) {
        String sql = "select * from order_detail where orderId = ?";
        return new ObjectUtil<List<OrderDetail>>().getList(sql, OrderDetail.class);
    }

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        String sql = "insert into order_detail values(?,?,?,?,?,?,?,?,?)";
        return new ObjectUtil<OrderDetail>().add(sql, OrderDetail.class, orderDetail.getId(), orderDetail.getOrderId(), orderDetail.getGoodsId(), orderDetail.getGoodTitle(), orderDetail.getGoodNum(), orderDetail.getPrice(), orderDetail.getIsDel(), orderDetail.getCreateTime(), orderDetail.getDeleteTime());
    }

    @Override
    public int updateOrderDetail(String id, OrderDetail orderDetail) {
        String sql = "update order_detail set orderId=?,goodsId=?,goodsTitle=?,goodNum=?,price=?,isDel=?,createTime=?,deleteTime=? where id=?";
        return new ObjectUtil<OrderDetail>().update(sql, orderDetail.getOrderId(), orderDetail.getGoodsId(), orderDetail.getGoodTitle(), orderDetail.getGoodNum(), orderDetail.getPrice(), orderDetail.getIsDel(), orderDetail.getCreateTime(), orderDetail.getDeleteTime(), orderDetail.getId());
    }

    @Override
    public int deleteOrderDetail(String id) {
        String sql = "delete from order_detail where id=?";
        return new ObjectUtil<OrderDetail>().delete(sql, id);
    }
}
