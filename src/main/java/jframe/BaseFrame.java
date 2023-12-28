package jframe;

import controller.CartController;
import controller.GoodsController;
import controller.OrderController;
import controller.UserController;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-21 09:12
 * @Description:
 */
public interface BaseFrame {
    UserController userController = new UserController();
    CartController cartController = new CartController();
    OrderController orderController = new OrderController();
    GoodsController goodsController = new GoodsController();
}
