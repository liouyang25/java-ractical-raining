package controller;

import dao.ISysLog;
import dao.impl.ConsoleLog;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 08:54
 * @Description:
 */
public class BaseController {
    /**
     * 上下文对象的key登录用户
     */
    public static final String LOGIN_USER = "LOGIN_USER";
    /**
     * 上下文对象 用来存储所有业务类的公共的信息
     */
    protected static final Map<String, Object> context = new HashMap<>();

    protected ISysLog log = new ConsoleLog();

    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
