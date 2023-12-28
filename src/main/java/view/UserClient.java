package view;

import controller.UserController;
import model.User;
import model.common.Msg;
import utils.JSONUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-30 09:11
 * @Description:
 */
public class UserClient extends Client {
    private final UserController userController = new UserController();
    //
//    private static final String NO = "N";
    //    private static final String USERNAME = "N";
//    private static final String PASSWORD = "P";


    private static final String LOGIN = "L";
    private static final String REGISTER = "R";
    private static final String UPDATE_USER_INFO = "U";
    private static final String YES = "Y";

    public String index() {
        System.out.println("==================用户中心==================");
        String result = userOperate("请根据选项操作", "L登录", (getCurrentUser() != null ? "C购物车" : "R注册"), "I首页", "U更新用户信息");
        while (true) {
            switch (result) {
                case LOGIN:
                    return showLogin();
                case REGISTER:
                    if (getCurrentUser() != null) {
                        System.out.println("无效操作");
                        return HISTORY;
                    }
                    return showRegister();
                case CART:
                    if (getCurrentUser() == null) {
                        System.out.println("无效操作");
                        return HISTORY;
                    }
                    return CART;
                case INDEX:
                    return INDEX;
                case UPDATE_USER_INFO:
                    return showUpdateUser();
                default:
                    System.out.println("输入有误, 请重新输入");
                    result = userOperate("请根据选项操作", "L登录", (getCurrentUser() != null ? "C购物车" : "R注册"), "I首页", "U更新用户信息");
            }
        }
    }

    public String showLogin() {
        if (getCurrentUser() != null) {
            System.out.println("是否退出用户" + getCurrentUser().getUsername() + "?(Y/N)");
            String result = scanner.next().trim().toUpperCase();
            while (true) {
                switch (result) {
                    case YES:
                        String username = getCurrentUser().getUsername();
                        userController.logout();
                        System.out.println("用户" + username + "退出成功");
                        return LOGIN;
                    case "N":
                        return HISTORY;
                    default:
                        System.out.println("输入有误, 请重新输入");
                        result = scanner.next().trim().toUpperCase();
                }
            }
        } else {
            System.out.println("==================欢迎登陆==================");
            System.out.println("请输入用户名: ");
            String username = scanner.next().trim();
            System.out.println("请输入密码: ");
            String password = scanner.next().trim();
            String jsonMessage = userController.login(username, password);
            Msg msg = JSONUtil.JSON2Entity(jsonMessage, Msg.class);
            if (msg.getType().equals(Msg.SUCCESS)) {
                System.out.println("【用户" + getCurrentUser().getUsername() + "登陆成功】");
                return HISTORY;
            } else {
                System.out.println("登录失败, 是否重新登陆?(Y/N)");
                return getString();
            }
        }
    }

    public String showRegister() {
        AtomicBoolean isExist = new AtomicBoolean(false);
        System.out.println("==================欢迎注册==================");
        System.out.println("请输入用户名: ");
        String username = scanner.next().trim();
        List<User> userList = userController.findUserByUsername(username);
        userList.forEach(user -> {
            if (user.getUsername().equals(username)) {
                isExist.set(true);
            }
        });
        if (isExist.get()) {
            System.out.println("用户名已存在, 是否前往登陆?(Y/N)");
            String result = scanner.next().trim().toUpperCase();
            if (YES.equals(result)) {
                return LOGIN;
            }
            return HISTORY;
        }
        String password;
        String passwordConfirm;
        while (true) {
            System.out.println("请输入密码: ");
            password = scanner.next().trim();
            if (password.length() >= 6) {
                break;
            }
            System.out.println("密码长度至少6位, 请重新输入");
        }
        while (true) {
            System.out.println("请再次输入密码: ");
            passwordConfirm = scanner.next().trim();
            if (passwordConfirm.length() >= 6) {
                break;
            }
            System.out.println("密码长度至少6位, 请重新输入");
        }
        if (!password.equals(passwordConfirm)) {
            System.out.println("两次输入密码不一致, 注册失败");
            return HISTORY;
        }
        User user = userController.setUserInfo(username, password);
        int affectedRows = userController.addUser(user);
        if (affectedRows > 0) {
            System.out.println("【" + user.getUsername() + "用户注册成功】");
        } else {
            System.out.println("注册失败, 是否重新注册?(Y/N)");
            String result = scanner.next().trim().toUpperCase();
            if (YES.equals(result)) {
                return REGISTER;
            }
        }
        return HISTORY;
    }


    public String showUpdateUser() {
        if (userController.getLoginUser() != null) {
            User user = userController.getLoginUser();
            System.out.println("请选择需要更新的用户信息: N用户名, P密码");
            String result = scanner.next().trim().toUpperCase();
            boolean isNorP = false;
            while (true) {
                switch (result) {
                    case "N":
                        System.out.println("请输入新的用户名: ");
                        String username = scanner.next().trim();
                        user.setUsername(username);
                        isNorP = true;
                        break;
                    case "P":
                        System.out.println("请输入新的密码: ");
                        String password = scanner.next().trim();
                        if (password.length() >= 6) {
                            user.setPassword(password);
                            isNorP = true;
                        } else {
                            System.out.println("密码长度至少6位, 请重新输入");
                        }
                        break;
                    default:
                        System.out.println("输入有误, 请重新输入");
                        result = scanner.next().trim().toUpperCase();
                }
                if (isNorP) {
                    int affectedRows = userController.updateUser(userController.getLoginUser());
                    if (affectedRows > 0) {
                        System.out.println("【用户信息更新成功】");
                        userController.logout();
                        System.out.println("【请重新登陆】");
                        return LOGIN;
                    } else {
                        return HISTORY;
                    }
                }
            }
        } else {
            System.out.println("还没有登陆账号, 是否现在登陆？(Y/N)");
            return getString();
        }
    }

    private String getString() {
        String result = scanner.next().trim().toUpperCase();
        while (true) {
            switch (result) {
                case "Y":
                    return LOGIN;
                case "N":
                    return HISTORY;
                default:
                    System.out.println("输入有误, 请重新输入");
                    result = scanner.next().trim().toUpperCase();
            }
        }
    }

    private User getCurrentUser() {
        return userController.getLoginUser();
    }
}
