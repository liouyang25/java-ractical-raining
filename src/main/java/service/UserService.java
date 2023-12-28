package service;

import model.User;

import java.util.List;

/**
 * @author li
 */
public interface UserService {
    /**
     * 用户登陆
     *
     * @param user
     * @return User
     */
    User login(User user);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 获取登陆用户
     *
     * @return User
     */
    User getLoginUser();

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return List<User>
     */
    List<User> findUserByUsername(String username);

    /**
     * 添加用户
     *
     * @param user User对象
     * @return 数据库影响的行数
     */
    int addUser(User user);

    /**
     * 更新用户信息
     *
     * @param user User对象
     * @return 数据库影响的行数
     */
    int updateUser(User user);
}
