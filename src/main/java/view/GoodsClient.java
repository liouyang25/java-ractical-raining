package view;

import controller.GoodsController;
import model.Goods;
import model.common.Msg;
import utils.JSONUtil;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-30 08:39
 * @Description:
 */
public class GoodsClient extends Client {
    private final GoodsController goodsController = new GoodsController();

    private static final String ADD = "A";

    public String index() {
        showGoodsList();
        String result = userOperate("请根据序号查看商品详情", "U用户中心", "I首页");
        while (true) {
            switch (result) {
                case USER:
                    return USER;
                case INDEX:
                    return INDEX;
                case EXIT:
                    return EXIT;
                case ADD:
                    if (currentGoods == null) {
                        System.out.println("输入错误, 请重新输入");
                        result = userOperate("请根据序号查看商品详情", "U用户中心", "I首页");
                        continue;
                    }
                    return ADD;
                default:
                    Goods goods = code2Goods.get(result);
                    if (goods != null) {
                        currentGoods = goods;
                        showGoodsDetail();
                        result = userOperate("请根据选项操作", "输入A加入购物车", "U用户中心", "I首页");
                    } else {
                        System.out.println("输入错误, 请重新输入");
                        result = userOperate("请根据序号查看商品详情", "U用户中心", "I首页");
                    }
                    break;
            }
        }
    }

    private void showGoodsList() {
        String goodsListJson = goodsController.getGoodsList();
        Msg msgObj = JSONUtil.JSON2Entity(goodsListJson, Msg.class);
        Object obj = msgObj.getObj();
        List<?> goodsListObj = (List<?>) obj;
        System.out.println("【商品列表】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t商品库存");
        int index = 1;
        for (Object o : goodsListObj) {
            String goodsJson = o.toString();
            Goods goods = JSONUtil.JSON2Entity(goodsJson, Goods.class);
            String name = goods.getName();
            String price = goods.getPrice() + "";
            String num = goods.getNumber() + "";
            System.out.println(index + ".\t\t" + name + "\t\t\t" + price + "\t\t" + num);
            code2Goods.put(index + "", goods);
            index++;
        }
    }

    private void showGoodsDetail() {
        System.out.println("【商品详情】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t商品库存\t\t品牌");
        String index = "1";
        String name = currentGoods.getName();
        String price = currentGoods.getPrice() + "";
        String num = currentGoods.getNumber() + "";
        String brand = currentGoods.getBrand();
        System.out.println(index + ".\t\t" + name + "\t\t" + price + "\t\t" + num + "\t\t" + brand);
    }
}