import dao.impl.IDataAccessImpl;
import model.OrderDetail;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 09:18
 * @Description:
 */
public class TestOrderDetailDao {
    IDataAccessImpl iDataAccess = new IDataAccessImpl();

    @Test
    public void testGetOrderDetails() throws Exception {
        List<OrderDetail> orderDetails = iDataAccess.getList(OrderDetail.class);
        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail);
        }

    }
    @Test
    public void testGetOrderDetailById() throws Exception {
        OrderDetail orderDetail = iDataAccess.getEntityById(OrderDetail.class, "0647a141ea3d4dfaa6f5f699be73b3f4");
        System.out.println(orderDetail);
    }
}
