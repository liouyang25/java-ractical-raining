package dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import dao.GoodsDAO;
import model.Goods;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 09:09
 * @Description:
 */
public class GoodsDAOImpl implements GoodsDAO {
    @Override
    public int addGoods(Goods goods) {
        String sql = "insert into goods values(?,?,?,?,?,?,?,?)";
        return new ObjectUtil<Goods>().add(sql, goods.getId(), goods.getCreateTime(), goods.getDeleteTime(), goods.getIsDel(), goods.getName(), goods.getPrice(), goods.getNumber(), goods.getBrand());
    }

    @Override
    public int deleteGoods(String id) {
        String sql = "delete from goods where id=?";
        return new ObjectUtil<Goods>().delete(sql, id);
    }

    @Override
    public int updateGoods(String id, Goods goods) {
        String sql = "update goods set createTime=?, deleteTime=?, isDel=?, name=?, price=?, number=?, brand=? where id=?";
        return new ObjectUtil<Goods>().update(sql, goods.getCreateTime(), goods.getDeleteTime(), goods.getIsDel(), goods.getName(), goods.getPrice(), goods.getNumber(), goods.getBrand(), id);
    }

    @Override
    public List<Goods> getGoodsByName(String name) {
        String sql = "select * from goods where name like ?";
        return new ObjectUtil<Goods>().getList(sql, Goods.class, "%" + name + "%");
    }
}
