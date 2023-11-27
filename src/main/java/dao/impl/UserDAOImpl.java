package dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import dao.UserDAO;
import model.User;

import java.util.List;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 10:03
 * @Description:
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public int addUser(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?)";
        return new ObjectUtil<User>().add(sql, user.getId(), user.getCreateTime(), user.getDeleteTime(), user.getIsDel(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public int updateUser(String id, User user) {
        String sql = "update user set username=?,password=?,role=?,createTime=?,deleteTime=?,isDel=? where id=?";
        return new ObjectUtil<User>().update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.getCreateTime(), user.getDeleteTime(), user.getIsDel(), id);
    }

    @Override
    public List<User> getUserByName(String username) {
        String sql = "select * from user where username like ?";
        return new ObjectUtil<User>().getList(sql, User.class, "%" + username + "%");
    }
}
