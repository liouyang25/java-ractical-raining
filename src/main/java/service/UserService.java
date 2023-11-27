package service;

import model.User;

/**
 * @author li
 */
public interface UserService {
    /**
     * 用户登陆
     * @param user
     * @return
     */
    User login(User user);

    /**
     *  获取登陆用户
     * @return
     */
    User getLoginUser();
}
