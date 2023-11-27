import dao.impl.IDataAccessImpl;
import model.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 09:18
 * @Description:
 */
public class TestOrderDao {
    IDataAccessImpl iDataAccess = new IDataAccessImpl();

    @Test
    public void testGetOrderList() {
        try {
            List<Order> orders = iDataAccess.getList(Order.class);
            for (Order order : orders) {
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = iDataAccess.getEntityById(Order.class, "025669aee0f345ee96407f294fa57dc7");
        System.out.println(order);
    }
}
