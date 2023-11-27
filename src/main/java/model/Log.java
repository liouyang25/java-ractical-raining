package model;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-27 10:52
 * @Description:
 */
public class Log {
    private String msg;
    private String level;
    private String time;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Log() {
    }

    public Log(String msg, String level, String time) {
        this.msg = msg;
        this.level = level;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + time + "]" + level + ": " + msg;
    }
}
