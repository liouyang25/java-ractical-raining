package controller;

import model.User;
import model.common.Msg;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.JSONUtil;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-23 12:40
 * @Description:
 */
public class UserController extends BaseController {
    private String username;
    private String password;
    private UserService userService;

    public UserController() {
        userService = new UserServiceImpl();
    }

    public String login() {
        Msg msg = new Msg();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.login(user);

        if (user != null) {
            context.put(LOGIN_USER, user);
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

    public User getLoginUser() {
        return context.get(LOGIN_USER) != null ? (User) context.get(LOGIN_USER) : null;
    }
}
