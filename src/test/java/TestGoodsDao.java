import dao.impl.IDataAccessImpl;
import model.Goods;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 09:18
 * @Description:
 */
public class TestGoodsDao {
    IDataAccessImpl iDataAccess = new IDataAccessImpl();

    @Test
    public void testGetGoodsById() throws Exception {
        Goods goods = iDataAccess.getEntityById(Goods.class, "8f709f1e023d4f02a4547fa17fc7a965");
        System.out.println(goods);
    }

    @Test
    public void testGetGoodsList() throws Exception {
        List<Goods> goods = iDataAccess.getList(Goods.class);
        for (Goods g : goods) {
            System.out.println(g);
        }
    }


}
