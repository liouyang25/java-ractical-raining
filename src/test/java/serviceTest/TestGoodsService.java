package serviceTest;

import model.Goods;
import org.junit.jupiter.api.Test;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-23 11:14
 * @Description:
 */
public class TestGoodsService {
    @Test
    public void testGoodsService() throws Exception {
        GoodsService goodsService = new GoodsServiceImpl();
        List<Goods> goodsList = goodsService.getGoodsListDb();
        System.out.println("测试列表查询");
        goodsList.forEach(goods -> {
            System.out.println(goods.toString());
        });
        System.out.println("测试商品名查询");
        List<Goods> goodsList1 = goodsService.findGoodsByName("子");
        goodsList1.forEach(goods -> {
            System.out.println(goods.toString());
        });
        System.out.println("测试根据id查询");
        Goods goods = goodsService.findGoodsById("31e57be4b9404d43a14f5b4ace83453d");
        System.out.println(goods.toString());
//        System.out.println("测试添加商品");
//        Goods goods1 = new Goods();
//        goods1.setId(UUID.randomUUID().toString());
//        goods1.setCreateTime(new Date());
//        goods1.setDeleteTime(null);
//        goods1.setIsDel("1");
//        goods1.setName("桃子");
//        goods1.setPrice(10.0);
//        goods1.setNumber(100);
//        goods1.setBrand("桃子");
//        goodsService.addGoods(goods1);
        System.out.println("测试更新商品信息");
        goods.setNumber(100);
        goodsService.updateGoods("31e57be4b9404d43a14f5b4ace83453d", goods);

    }
}
