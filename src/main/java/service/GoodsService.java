package service;

import model.Goods;

import java.util.List;

/**
 * @author li
 */
public interface GoodsService {
    /**
     * 获取商品列表
     *
     * @return 商品列表
     * @throws Exception 数据库异常
     */
    List<Goods> getGoodsListDb() throws Exception;

    /**
     * 根据商品名称查询
     *
     * @param name 商品名称
     * @return 商品列表
     * @throws Exception 数据库异常
     */
    List<Goods> findGoodsByName(String name);

    /**
     * 根据id查找商品
     *
     * @param id 商品id
     * @return Goods类
     * @throws Exception 数据库异常
     */
    Goods findGoodsById(String id) throws Exception;


    /**
     * 修改商品
     *
     * @param id    商品id
     * @param goods Goods类
     * @return 数据库影响的行数
     */
    int updateGoods(String id, Goods goods);

    /**
     * 添加商品
     *
     * @param goods Goods类
     * @return 数据库影响的行数
     */
    int addGoods(Goods goods);


    /**
     * 删除商品
     *
     * @param id 商品id
     * @return 数据库影响的行数
     */
    int delGoods(String id);

    /**
     * 根据多个id查找商品
     *
     * @param ids 商品id
     * @return 商品列表
     * @throws Exception 数据库异常
     */
    List<Goods> findGoodsByIds(String ids) throws Exception;
}
