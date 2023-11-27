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
     * @return
     * @throws Exception
     */
    List<Goods> getGoodsListDb() throws Exception;

    /**
     *根据商品名称查询
     * @param name
     * @return
     * @throws Exception
     */
    List<Goods>  findGoodsByName(String name) throws Exception;

    /**
     * 根据id查找商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    Goods findGoodsById(String id) throws Exception;


    /**
     * 修改商品
     *
     * @param id
     * @param goods
     * @return
     */
    int updateGoods(String id, Goods goods);

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
    int delGoods(String id);

    /**
     * 根据多个id查找商品
     *
     * @param ids
     * @return
     * @throws Exception
     */
    List<Goods> findGoodsByIds(String ids) throws Exception;
}
