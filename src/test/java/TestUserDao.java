import dao.impl.IDataAccessImpl;
import dao.impl.UserDAOImpl;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 10:33
 * @Description:
 */
public class TestUserDao {
    UserDAOImpl userDAOImpl = new UserDAOImpl();
    IDataAccessImpl iDataAccess = new IDataAccessImpl();

    @Test
    public void testGetUserById() throws Exception {
        User user = iDataAccess.getEntityById(User.class, "cfba49439ddd11eb891700ff26ca915f");
        System.out.println(user);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(User.getUUID());
        user.setCreateTime(new Date());
        user.setDeleteTime(new Date());
        user.setIsDel("1");
        user.setUsername("li");
        user.setPassword("123456");
        user.setRole(User.NORMAL);
        int affectedRows = userDAOImpl.addUser(user);
        if (affectedRows > 0) {
            System.out.println("用户添加成功");
        } else {
            System.out.println("用户添加失败");
        }
    }

    @Test
    public void testGetUserList() {
        try {
            List<User> users = iDataAccess.getList(User.class);
            for (User user : users) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
