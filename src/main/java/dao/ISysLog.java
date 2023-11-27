package dao;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-15 19:37
 * @Description:
 */
public interface ISysLog {
    /**
     * 日志级别 普通消息
     */
    public static final String INFO = "INFO";
    /**
     * 日志级别 警告消息
     */
    public static final String WARN = "WARN";
    /**
     * 日志级别 错误消息
     */
    public static final String ERROR = "ERROR";

    void info(String msg);

    void warn(String msg);

    void error(String msg);
}
