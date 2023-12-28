package view;

import controller.CartController;
import controller.UserController;
import model.CartGoods;
import model.common.Msg;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import utils.JSONUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-04 12:48
 * @Description:
 */
public class CartClient extends Client {
    private static final GoodsService goodsService = new GoodsServiceImpl();
    private final CartController cartController = new CartController();
    private final UserController userController = new UserController();
    Map<String, CartGoods> code2CartGoods = new HashMap<>();

    public String index() {
        showCartList();
        String result = userOperate("请根据选项操作", "D删除购物车", "O结算", "I首页");
        while (true) {
            switch (result) {
                case INDEX:
                    return INDEX;
                case ORDER:
                    return ORDER;
                case "D":
                    return deleteCart();
                case EXIT:
                    return EXIT;
                default:
                    System.out.println("输入错误, 请重新输入(CartClient.index): ");
                    result = userOperate("请根据选项操作", "D删除购物车", "I首页");
                    break;
            }
        }
    }

    private String deleteCart() {
        showCartList();
        System.out.println("请输入需要删除的商品的编号: ");
        String index = scanner.next().trim();
        String goodsId;
        while (true) {
            try {
                goodsId = code2CartGoods.get(index).getId();
                break;
            } catch (Exception e) {
                System.out.println("输入的商品编号不存在, 请重新输入(CartClient.deleteCart): ");
                index = scanner.next().trim();
            }
        }
        System.out.println("请输入需要删除的数量: ");
        String numberStr = scanner.next().trim();
        int number;
        while (true) {
            try {
                number = Integer.parseInt(numberStr);
                break;
            } catch (Exception e) {
                System.out.println("输入错误, 请重新输入(CartClient.deleteCart): ");
                numberStr = scanner.next().trim();
            }
        }
        String deleteCartResultJson = cartController.deleteCart(goodsId, number, code2CartGoods.get(index));
        Msg msgObj = JSONUtil.JSON2Entity(deleteCartResultJson, Msg.class);
        if (msgObj.getType().equals(Msg.SUCCESS)) {
            System.out.println("购物车商品删除成功");
        } else {
            System.out.println("购物车商品删除失败, 是否重新执行操作(Y/N)?: ");
            String reExecute = scanner.next().trim().toUpperCase();
            while (true) {
                if ("Y".equals(reExecute)) {
                    deleteCart();
                } else if ("N".equals(reExecute)) {
                    return CART;
                } else {
                    System.out.println("输入错误, 请重新输入(CartClient.deleteCart): ");
                    reExecute = scanner.next().trim().toUpperCase();
                }
            }
        }
        return CART;
    }

    public void showCartList() {
        String cartListJson = cartController.showCart();
        Msg msgObj = JSONUtil.JSON2Entity(cartListJson, Msg.class);
        Object obj = msgObj.getObj();
        List<?> cartListObj = (List<?>) obj;
        if (cartListObj.isEmpty()) {
            System.out.println("【购物车为空】");
            return;
        }
        System.out.println("【购物车列表】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t数量");
        int index = 1;
        for (Object o : cartListObj) {
            String goodsJson = o.toString();
            CartGoods cartGoods = JSONUtil.JSON2Entity(goodsJson, CartGoods.class);
            code2CartGoods.put(String.valueOf(index), cartGoods);
            String name = cartGoods.getName();
            String price = cartGoods.getPrice() + "";
            String num = cartGoods.getGoodsNum() + "";
            System.out.println(index + ".\t\t" + name + "\t\t\t" + price + "\t\t" + num);
            index++;
        }
    }

    public String addCart() {
        if (userController.getLoginUser() == null) {
            System.out.println("您还未登录, 是否前往登陆?(Y/N)");
            String result = scanner.next().trim().toUpperCase();
            while (true) {
                if ("Y".equals(result)) {
                    HISTORY = INDEX;
                    return LOGIN;
                } else if ("N".equals(result)) {
                    return INDEX;
                } else {
                    System.out.println("输入错误, 请重新输入");
                    result = scanner.next().trim().toUpperCase();
                }
            }
        } else {
            String status = cartController.addCart(currentGoods);
            Msg msgObj = JSONUtil.JSON2Entity(status, Msg.class);
            if (msgObj.getType().equals(Msg.SUCCESS)) {
                System.out.println(msgObj.getMsg());
                String result = userOperate("请根据选项操作", "C查看购物车", "I查看商品列表");
                while (true) {
                    if (result.equals(CART)) {
                        return CART;
                    } else if (result.equals(INDEX)) {
                        return INDEX;
                    } else {
                        System.out.println("输入错误, 请重新输入");
                        result = userOperate("请根据选项操作", "C查看购物车", "I查看商品列表");
                    }
                }
            } else {
                System.out.println(msgObj.getMsg());
                return HISTORY;
            }
        }
    }

    public static Double calculateTotal(Map<String, Integer> map) {
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        // 保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        double total = 0;
        for (Map.Entry<String, Integer> entry : set) {
            String id = entry.getKey();
            Integer num = entry.getValue();
            double price = 0;
            try {
                price = goodsService.findGoodsById(id).getPrice();
            } catch (Exception e) {
                e.printStackTrace();
            }
            total += (price * num);
        }
        return Double.parseDouble(df.format(total));
    }
}
