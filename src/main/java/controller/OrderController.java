package controller;

import model.Order;
import model.common.Msg;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.JSONUtil;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 09:07
 * @Description:
 */
public class OrderController extends BaseController {
    private Order order;
    private OrderService orderService;

    public OrderController() {
        orderService = new OrderServiceImpl();
    }

    public String addOrder2Db(Order order) {
        Msg msg = new Msg();
        int affectedRows = orderService.addOrder(order);
        if (affectedRows > 0) {
            msg.setType(Msg.SUCCESS);
            msg.setMsg("添加订单");
            log.info("添加订单成功");
        } else {
            msg.setType(Msg.FAIL);
            msg.setMsg("订单添加失败");
            log.error("添加订单失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    public String findOrder(Order order) {
        //TODO
        return "";
    }

    public String updateOrder(Order curOrder) {
        Msg msg = new Msg();
        int affectedRows = orderService.updateOrderById(curOrder.getId(), curOrder);
        if (affectedRows > 0) {
            msg.setType(Msg.SUCCESS);
            msg.setMsg("更新订单成功");
            log.info("更新订单成功");
        } else {
            msg.setType(Msg.FAIL);
            msg.setMsg("更新订单失败");
            log.error("更新订单失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    public String deleteOrder(String id) {
        Msg msg = new Msg();
        int affectedRows = orderService.deleteOrder(id);
        if (affectedRows > 0) {
            msg.setType(Msg.SUCCESS);
            msg.setMsg("删除订单成功");
            log.info("删除订单");
        } else {
            msg.setType(Msg.FAIL);
            msg.setMsg("删除订单失败");
            log.error("删除订单失败");
        }
        return JSONUtil.entity2JSON(msg);
    }
}
