package controller;

import model.User;
import model.common.Msg;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.JSONUtil;

import java.util.Date;
import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-23 12:40
 * @Description:
 */
public class UserController extends BaseController {
    private UserService userService;

    public UserController() {
        userService = new UserServiceImpl();
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 登陆成功或失败的消息
     */
    public String login(String username, String password) {
        Msg msg = new Msg();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.login(user);

        if (user != null) {
            CONTEXT.put(LOGIN_USER, user);
            msg.setType(Msg.SUCCESS);
            msg.setMsg("用户登录成功");
            log.info("用户登陆成功");
        } else {
            msg.setType(Msg.FAIL);
            msg.setMsg("用户名或密码错误");
            log.info("用户登录失败");
        }
        return JSONUtil.entity2JSON(msg);
    }

    /**
     * 获取当前登录用户
     *
     * @return 用户
     */
    public User getLoginUser() {
        return userService.getLoginUser();
    }

    /**
     * 登出
     */
    public void logout() {
        userService.logout();
    }

    /**
     * 根据用户名查找对应用户
     *
     * @param username 用户名
     * @return 用户列表
     */
    public List<User> findUserByUsername(String username) {
        return userService.findUserByUsername(username);
    }

    /**
     * 生成一个User对象
     *
     * @param username 用户名
     * @param password 密码
     * @return User对象
     */
    public User setUserInfo(String username, String password) {
        User user = new User();
        user.setId(User.getUUID());
        user.setCreateTime(new Date());
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(User.NORMAL);
        return user;
    }

    /**
     * 添加用户
     *
     * @param user User对象
     * @return 影响数据库的行数
     */
    public int addUser(User user) {
        return userService.addUser(user);
    }

    /**
     * 更新用户信息
     *
     * @param user User对象
     * @return 影响数据库的行数
     */
    public int updateUser(User user) {
        return userService.updateUser(user);
    }
}
