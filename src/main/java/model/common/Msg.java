package model.common;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 09:25
 * @Description:
 */
public class Msg {
    /**
     * 消息类型：成功
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 消息类型：失败
     */
    public static final String FAIL = "FAIL";

    private String type;
    private String msg;
    private Object obj;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
