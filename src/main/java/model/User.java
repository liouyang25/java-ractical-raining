package model;

import model.common.Entity;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 09:46
 * @Description:
 */
public class User extends Entity {
    public static final String NORMAL = "NORMAL";
    public static final String ADMIN = "ADMIN";
    private String username;
    private String password;
    private String role = "NORMAL";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", createTime=" + getCreateTime() +
                ", deleteTime=" + getDeleteTime() +
                ", isDel='" + getIsDel() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
