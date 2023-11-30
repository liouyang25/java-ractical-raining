package dao.impl;

import dao.ISysLog;
import model.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-20 09:19
 * @Description:
 */
public class ConsoleLog implements ISysLog {


    @Override
    public void info(String msg) {
        printLog(msg, INFO);
    }

    @Override
    public void warn(String msg) {
        printLog(msg, WARN);
    }

    @Override
    public void error(String msg) {
        printLog(msg, ERROR);
    }

    public void printLog(String msg, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        String log = new Log(msg, type, simpleDateFormat.format(new Date())).toString();
        System.out.println(log);
    }
}
