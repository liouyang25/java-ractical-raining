package service.impl;

import service.BaseService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-23 10:54
 * @Description:
 */
public class BaseServiceImpl implements BaseService {
    /**
     * 上下文对象的key 登录用户
     */
    public static final String LOGIN_USER = "LOGIN_USER";
    /**
     * 上下文对象 用来存储所有业务类的公共信息
     */

    protected static final Map<String, Object> context = new HashMap<>();
}
