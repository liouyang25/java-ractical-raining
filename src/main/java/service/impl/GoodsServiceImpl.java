package service.impl;

import dao.GoodsDAO;
import dao.IDataAccess;
import dao.impl.GoodsDAOImpl;
import dao.impl.IDataAccessImpl;
import model.Goods;
import service.GoodsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 09:33
 * @Description:
 */
public class GoodsServiceImpl implements GoodsService {
    private GoodsDAO goodsDAO;
    private IDataAccess iDataAccess;

    public GoodsServiceImpl() {
        goodsDAO = new GoodsDAOImpl();
        iDataAccess = new IDataAccessImpl();
    }

    @Override
    public List<Goods> getGoodsListDb() {
        return iDataAccess.getList(Goods.class);
    }

    @Override
    public Goods findGoodsById(String id) throws Exception {
        return iDataAccess.getEntityById(Goods.class, id);
    }

    @Override
    public List<Goods> findGoodsByName(String name) {
        return goodsDAO.getGoodsByName(name);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsDAO.addGoods(goods);
    }

    @Override
    public int updateGoods(String id, Goods goods) {
        return goodsDAO.updateGoods(id, goods);
    }

    @Override
    public int delGoods(String id) {
        return goodsDAO.deleteGoods(id);
    }

    @Override
    public List<Goods> findGoodsByIds(String ids) {
        List<Goods> allGoods = getGoodsListDb();
        List<Goods> goodsList = new ArrayList<>();
        for (Goods goods : allGoods) {
            if (ids.contains((goods.getId()))) {
                goodsList.add(goods);
            }
        }
        return goodsList;
    }
}
