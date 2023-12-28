package view;

import controller.CartController;
import controller.GoodsController;
import controller.OrderController;
import controller.UserController;
import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.Goods;
import model.Order;
import model.common.Msg;
import utils.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-04 12:49
 * @Description:
 */
public class OrderClient extends Client {
    private static final String PAY = "P";
    private static final String SD = "SD";
    private static final String DELETE = "D";
    OrderController orderController;
    GoodsController goodsController;
    OrderDAO orderDAO;
    CartController cartController;

    public OrderClient() {
        orderController = new OrderController();
        goodsController = new GoodsController();
        orderDAO = new OrderDAOImpl();
        cartController = new CartController();
    }

    public String index() {
        return showOrder();
    }

    public String showOrder() {
        System.out.println("============= 正在生成订单 ==============");
        // 输入 收货人 收货地址 联系电话
        System.out.println("请输入收货人:");
        String consignee = scanner.next();
        System.out.println("请输入收货地址:");
        String consigneeAddress = scanner.next();
        System.out.println("请输入联系电话:");
        String phone = scanner.next();
        //生成订单
        orderController.createOrder(consignee, consigneeAddress, phone);
        // 输入操作
        String result = userOperate("请选择操作", "P去支付", "SD查看订单详情", "I首页");
        while (true) {
            switch (result) {
                case PAY:
                    result = handlePay();
                    break;
                case INDEX:
                    return INDEX;
                case SD:
                    result = checkOrder();
                    break;
                case EXIT:
                    return EXIT;
                default:
                    System.out.println("输入不正确，请重新输入:");
                    result = userOperate("请选择操作", "P去支付", "SD查看订单详情", "I首页");
                    break;
            }
        }
    }

    public String handlePay() {
        Map<String, Integer> goodsCart = CartController.getCart();
        //将返回值封装到Msg类中
        Msg msg = new Msg();
        String status;
        while (true) {
            System.out.println("请输入支付金额: ");
            String money = scanner.next();
            OrderDAO orderDAO = new OrderDAOImpl();
            Order orders;
            orders = orderDAO.getOrderById(OrderController.id);
            // 支付金额
            double amount = orders.getAmount();
            // 获取库存数量
            for (Map.Entry<String, Integer> entry : goodsCart.entrySet()) {
                //根据id获取数据
                Goods goods = goodsController.getGoodsById1(entry.getKey());
                // 库存
                int num = goods.getNumber();
                // 剩余
                num -= entry.getValue();
                goods.setNumber(num);
                if (goods.getNumber() >= 0 && Double.parseDouble(money) > amount) {
                    // 更新goods表
                    status = goodsController.updateGoodsById(entry.getKey(), goods);
                    //解析msg
                    msg = JSONUtil.JSON2Entity(status, Msg.class);
                    // 更新order表
                    UserController userController = new UserController();
                    orders.setUserId(userController.getLoginUser().getId());
                    orders.setState("已支付");
                    orderDAO.updateOrderById(OrderController.id, orders);
                } else {
                    msg.setType(Msg.FAIL);
                }
            }
            if (msg.getType().equals(Msg.SUCCESS)) {

                System.out.println("支付成功!");
                System.out.println("返还金额: " + (Double.parseDouble(money) - amount));
                // 清空
                CartController.getCart().clear();
                Order order = orderController.getOrder();
                order.setState("已支付");
                orderController.setOrder(order);
                break;
            } else {
                System.out.println("商品库存不足或支付金额不足");
                System.out.println("是否重新支付？(Y/N): ");
                String str = scanner.next();
                if ("N".equalsIgnoreCase(str.trim())) {
                    break;
                }
            }
        }
        String result = userOperate("请选择操作", "SD查看订单详情", "I首页");
        while (true) {
            switch (result) {
                case INDEX:
                    return INDEX;
                case SD:
                    result = checkOrder();
                    break;
                case EXIT:
                    return EXIT;
                default:
                    System.out.println("输入不正确，请重新输入: ");
                    result = userOperate("请选择操作", "SD查看订单详情", " I首页");
                    break;
            }
        }
    }

    public String checkOrder() {
        orderController.showOrder();
        String result = userOperate("请选择操作", "P去支付", "D删除订单", "I首页");
        while (true) {
            switch (result) {
                case INDEX:
                    return INDEX;
                case PAY:
                    result = handlePay();
                    break;
                case EXIT:
                    return EXIT;
                case DELETE:
                    deleteOrder();
                    return SD;
                default:
                    System.out.println("输入不正确，请重新输入: ");
                    result = userOperate("请选择操作", "P去支付", "D删除订单", "I首页");
                    break;
            }
        }
    }

    //删除订单
    public void deleteOrder() {
        System.out.println("请输入要删除的订单号: ");
        String id = scanner.next();
        if (isExists(id)) {
            return;
        }
        String s = orderController.deleteOrder(id);
        Msg msgObj = JSONUtil.JSON2Entity(s, Msg.class);
        if (msgObj.getType().equals(Msg.SUCCESS)) {
            System.out.println("订单删除成功");
            cartController.deleteAllCart();
        } else {
            System.out.println("订单删除失败");
        }
    }

    public boolean isExists(String orderId) {
        boolean isExistence = false;
        for (String id : getOrderID()) {
            if (id.equals(orderId)) {
                isExistence = true;
            }
        }
        if (!isExistence) {
            System.out.println("该订单号不存在! ");
            return isExistence;
        }
        return !isExistence;
    }

    //获得所有订单编号
    public List<String> getOrderID() {
        List<Order> orderList = orderDAO.selectOrder();
        List<String> stringList = new ArrayList<>();
        for (Order o : orderList) {
            stringList.add(o.getSerialNumber());
        }
        return stringList;
    }
}
