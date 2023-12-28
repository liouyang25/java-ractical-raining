package controller;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.Order;
import model.common.Entity;
import model.common.Msg;
import service.GoodsService;
import service.OrderService;
import service.impl.GoodsServiceImpl;
import service.impl.OrderServiceImpl;
import utils.JSONUtil;
import view.CartClient;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 09:07
 * @Description:
 */
public class OrderController extends BaseController {
    public static String serialNum = null;
    public static String id = null;
    Entity entity;

    private final OrderService orderService;
    private final GoodsService goodsService;
    private final UserController userController;
    private final Map<String, Integer> map;

    public OrderController() {
        orderService = new OrderServiceImpl();
        userController = new UserController();
        map = CartController.getCart();
        entity = new Entity();
        goodsService = new GoodsServiceImpl();
    }

    public void createOrder(String consignee, String consigneeAddress, String phone) {
        Order order = new Order();
        order.setConsignee(consignee);
        order.setConsigneeAddress(consigneeAddress);
        order.setPhone(phone);
        serialNum = UUID.randomUUID().toString();
        id = Entity.getUUID();
        String userId = userController.getLoginUser().getId();
        order.setUserId(userId);
        order.setId(id);
        order.setSerialNumber(serialNum);
        order.setState("待支付");
        Double amount = CartClient.calculateTotal(map);
        order.setAmount(amount);

        System.out.println("============= 订单已生成 ==============");
        System.out.println("订单号:\t" + order.getSerialNumber());
        System.out.println("总金额:\t" + order.getAmount());
        System.out.println("状态:  \t" + order.getState());
        Date now = new Date();
        entity.setCreateTime(now);
        order.setCreateTime(now);
        CONTEXT.put(ORDER_USER, order);
        OrderDAO orderDAO = new OrderDAOImpl();
        orderDAO.insertOrder(order);
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
        int affectedRows = orderService.deleteOrderBySN(id);
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

    public void showOrder() {
        Order order = getOrder();
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        System.out.println("【我的订单】");
        System.out.print("订单号: " + order.getSerialNumber() + "\t");
        System.out.print("下单时间: " + sdf.format(entity.getCreateTime()) + "\t");
        System.out.print("总金额: " + order.getAmount() + "\t");
        System.out.println("状态: " + order.getState());
        for (Map.Entry<String, Integer> entry : set) {
            String id = entry.getKey();
            Integer num = entry.getValue();
            String name = null;
            double price = 0;
            try {
                name = goodsService.findGoodsById(id).getName();
                price = goodsService.findGoodsById(id).getPrice();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("商品名称: " + name + "\t");
            System.out.print("已选数量: " + num + "\t");
            System.out.println("小计金额: " + num * price);
        }
    }

    public Order getOrder() {
        Object obj = CONTEXT.get(ORDER_USER);
        //判断对象是否为空，不为空则进行类型转换后返回
        if (obj != null) {
            return (Order) obj;
        }
        return null;
    }

    public void setOrder(Order order) {
        CONTEXT.put(ORDER_USER, order);
    }
}
