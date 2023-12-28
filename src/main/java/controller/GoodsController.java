package controller;

import model.Goods;
import model.common.Msg;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import utils.JSONUtil;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 08:54
 * @Description:
 */
public class GoodsController extends BaseController {
    private Goods goods;
    private final GoodsService goodsService;

    public GoodsController() {
        goodsService = new GoodsServiceImpl();
    }

    public String getGoodsList() {
        Msg msg = new Msg();
        try {
            List<Goods> goodsList = goodsService.getGoodsListDb();
            msg.setObj(goodsList);
            msg.setType(Msg.SUCCESS);
            log.info("获取商品列表");
            return JSONUtil.entity2JSON(msg);
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg("商品列表获取失败");
            log.error("商品列表获取失败" + e.getMessage());
        }
        return JSONUtil.entity2JSON(msg);
    }

    public String getGoodsDetail(String name) {
        Msg msg = new Msg();
        try {
            Goods goods = goodsService.findGoodsByName(name).get(0);
            msg.setObj(goods);
            msg.setType(Msg.SUCCESS);
            log.info("获取商品" + name + "详情");
            return JSONUtil.entity2JSON(msg);
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg("商品详情获取失败");
            log.error("商品详情获取失败" + e.getMessage());
        }
        return JSONUtil.entity2JSON(msg);
    }


    public String getGoodsById(String id) {
        Msg msg = new Msg();
        try {
            Goods goods = goodsService.findGoodsById(id);
            msg.setObj(goods);
            msg.setType(Msg.SUCCESS);
            log.info("获取商品" + goods.getName());
            return JSONUtil.entity2JSON(msg);
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg("获取商品" + goods.getName() + "失败");
            log.error("获取商品" + goods.getName() + "失败" + e.getMessage());
        }

        return JSONUtil.entity2JSON(msg);
    }

    public Goods getGoodsById1(String id) {
        Msg msg = new Msg();
        Goods goods = null;
        try {
            goods = goodsService.findGoodsById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setObj(goods);
        msg.setType(Msg.SUCCESS);
        return goods;
    }

    public String updateGoodsById(String id, Goods goods) {
        Msg msg = new Msg();
        int affectedRows = goodsService.updateGoods(id, goods);
        if (affectedRows > 0) {
            msg.setType(Msg.SUCCESS);
            msg.setMsg("更新商品" + goods.getName() + "成功");
            log.info("更新商品" + goods.getName());
        } else {
            msg.setType(Msg.FAIL);
            msg.setMsg("更新商品" + goods.getName() + "失败");
            log.error("更新商品" + goods.getName() + "失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    public String addGoods(Goods goods) {
        Msg msg = new Msg();
        int affectedRows = goodsService.addGoods(goods);
        if (affectedRows > 0) {
            msg.setType(Msg.SUCCESS);
            msg.setMsg("添加商品" + goods.getName() + "成功");
            log.info("添加商品" + goods.getName());
        } else {
            msg.setMsg(Msg.FAIL);
            msg.setMsg("添加商品" + goods.getName() + "失败");
            log.error("添加商品" + goods.getName() + "失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    public String delGoods(String id) {
        Msg msg = new Msg();
        int affectedRows = goodsService.delGoods(id);
        if (affectedRows > 0) {
            msg.setType(Msg.SUCCESS);
            msg.setMsg("删除商品" + goods.getName() + "成功");
            log.info("删除商品" + goods.getName());
        } else {
            msg.setMsg(Msg.FAIL);
            msg.setMsg("删除商品" + goods.getName() + "失败");
            log.error("删除商品" + goods.getName() + "失败");
        }
        return JSONUtil.entity2JSON(msg);
    }
}
