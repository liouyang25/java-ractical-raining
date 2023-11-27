package model.common;

import java.util.Date;
import java.util.UUID;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-09 08:53
 * @Description:
 */
public class Entity {
    private String id;
    private Date createTime;
    private Date deleteTime;
    private String isDel = "1";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
