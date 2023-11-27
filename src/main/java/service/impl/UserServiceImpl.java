package service.impl;

import dao.IDataAccess;
import dao.impl.IDataAccessImpl;
import model.User;
import service.UserService;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-16 10:53
 * @Description:
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private IDataAccess iDataAccess;

    public UserServiceImpl() {
        iDataAccess = new IDataAccessImpl();
    }

    @Override
    public User login(User user) {
        List<User> userList = iDataAccess.getList(User.class);
        if (userList != null) {
            for (User u : userList) {
                if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                    context.put(LOGIN_USER, u);
                    return u;
                }
            }

        }
        return null;
    }

    @Override
    public User getLoginUser() {
        Object obj = context.get(LOGIN_USER);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }


}
