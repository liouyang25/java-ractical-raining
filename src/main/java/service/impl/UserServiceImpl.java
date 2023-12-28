package service.impl;

import dao.IDataAccess;
import dao.UserDAO;
import dao.impl.IDataAccessImpl;
import dao.impl.UserDAOImpl;
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
    private UserDAO userDAO;

    public UserServiceImpl() {
        iDataAccess = new IDataAccessImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public User login(User user) {
        List<User> userList = iDataAccess.getList(User.class);
        if (userList != null) {
            for (User u : userList) {
                if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                    CONTEXT.put(LOGIN_USER, u);
                    return u;
                }
            }

        }
        return null;
    }

    @Override
    public void logout() {
        CONTEXT.remove(LOGIN_USER);
    }

    @Override
    public User getLoginUser() {
        Object obj = CONTEXT.get(LOGIN_USER);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return userDAO.getUserByName(username);
    }

    @Override
    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDAO.updateUser(user.getId(), user);
    }
}
