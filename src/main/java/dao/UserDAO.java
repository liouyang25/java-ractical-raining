package dao;

import model.User;

import java.util.List;

/**
 * @author li
 */
public interface UserDAO {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 更新用户
     *
     * @param id
     * @param user
     * @return
     */
    int updateUser(String id, User user);

    /**
     * 通过姓名查询用户
     *
     * @param username
     * @return
     */
    List<User> getUserByName(String username);
}
