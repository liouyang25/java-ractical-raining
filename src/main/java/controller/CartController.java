package controller;

import model.Goods;
import model.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 09:09
 * @Description:
 */
public class CartController extends BaseController {
    private Goods goods;

    /**
     * 购物车对象
     * key： 商品 ID
     * value： 商品数量
     */
    private static Map<String, Integer> cart = new HashMap<>();

    public String addCart() {
        return "";
    }

    public String showCart() {
        return "";
    }

    public String updateOrder(Order curOrder) {
        return "";
    }
}
