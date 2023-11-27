package dao;

import model.Goods;

import java.util.List;

/**
 * @author li
 */
public interface GoodsDAO {
    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    int addGoods(Goods goods);

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    int deleteGoods(String id);

    /**
     * 修改商品
     *
     * @param id
     * @param goods
     * @return
     */
    int updateGoods(String id, Goods goods);

    /**
     * 根据名字获取商品
     *
     * @param name
     * @return
     */
    List<Goods> getGoodsByName(String name);
}
