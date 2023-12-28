package controller;

import model.CartGoods;
import model.Goods;
import model.Order;
import model.common.Msg;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import utils.JSONUtil;

import java.util.*;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 09:09
 * @Description:
 */
public class CartController extends BaseController {
    private final GoodsService goodsService;

    /**
     * 购物车对象
     * key： 商品 ID
     * value： 商品数量
     */
    private static final Map<String, Integer> CART = new HashMap<>();

    public CartController() {
        goodsService = new GoodsServiceImpl();
    }

    public String addCart(Goods goods) {
        Msg msg = new Msg();
        try {
            Integer num = CART.get(goods.getId());
            if (num != null && num > 0) {
                CART.put(goods.getId(), num + 1);
            } else {
                CART.put(goods.getId(), 1);
            }
            msg.setType(Msg.SUCCESS);
            msg.setMsg("添加购物车成功");
            log.info("添加购物车成功");
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg("添加购物车失败");
            log.error("添加购物车失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    public String deleteCart(String goodsId, int number, Goods goods) {
        System.out.println(goods);
        Msg msg = new Msg();
        if (number > CART.get(goodsId)) {
            msg.setType(Msg.FAIL);
            msg.setMsg("删除的商品数量小于添加的数量");
            log.error("删除的商品数量小于添加的数量");
        } else {
            System.out.println("是否确认删除商品(Y/N)?: ");
            String isConfirm = scanner.next().trim().toUpperCase();
            if (isConfirm.equals("Y")) {
                for (int i = 0; i < number; i++) {
                    CART.remove(goodsId);
                }
                msg.setType(Msg.SUCCESS);
                msg.setMsg(goods.getName() + "删除成功");
                log.info("删除购物车成功");
            }


        }
        return JSONUtil.entity2JSON(msg);
    }

    public String deleteAllCart() {
        CART.clear();
        return "清空购物车成功";
    }

    public String updateCart(String goodsId, int number) {
        Msg msg = new Msg();
        if (number > CART.get(goodsId)) {
            msg.setType(Msg.FAIL);
        }
        scanner.next();
        return "";

//        Msg msg = new Msg();
//        CartGoods cartGoods = cartGoodsMap.get(index);
//        System.out.println(cartGoods);
//
//        if (cartGoods == null) {
//            msg.setType(Msg.FAIL);
//            msg.setMsg("商品不存在");
//        } else {
//            System.out.println("是否确认删除" + cartGoods.getName() + "?(Y/N): ");
//            String result = scanner.next().trim().toUpperCase();
//            System.out.println(result);
//            boolean continueInput = true;
//            while (continueInput) {
//                if ("Y".equals(result)) {
//                    cartGoodsMap.remove(index);
//                    msg.setType(Msg.SUCCESS);
//                    msg.setMsg(cartGoods.getName() + "删除成功");
//                    continueInput = false;
//                } else if ("N".equals(result)) {
//                    msg.setType(Msg.FAIL);
//                    msg.setMsg("删除操作取消");
//                    continueInput = false;
//                } else {
//                    System.out.println("输入错误, 请重新输入: ");
//                    result = scanner.next().trim().toUpperCase();
//                }
//            }
//        }
//        return JSONUtil.entity2JSON(msg);

    }

    public String showCart() {
        Msg msg = new Msg();
        String ids = Arrays.toString(CART.keySet().toArray());
        ArrayList<CartGoods> cartGoodsList = new ArrayList<>();
        try {
            List<Goods> goodsList = goodsService.findGoodsByIds(ids);
            for (Goods g : goodsList) {
                CartGoods cartGoods = new CartGoods();
                cartGoods.setId(g.getId());
                cartGoods.setGoodsNum(CART.get(g.getId()));
                cartGoods.setName(g.getName());
                cartGoods.setPrice(g.getPrice());
                cartGoodsList.add(cartGoods);
            }
            msg.setType(Msg.SUCCESS);
            msg.setObj(cartGoodsList);
            log.info("购物车数据：" + cartGoodsList);
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg("购物车数据获取失败");
            log.error("购物车数据获取失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    public static Map<String, Integer> getCart() {
        return CART;
    }

    public String updateOrder(Order curOrder) {
        return "";
    }
}
