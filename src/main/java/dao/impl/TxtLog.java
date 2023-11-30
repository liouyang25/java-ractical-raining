package dao.impl;

import dao.ISysLog;
import model.Log;
import utils.FileUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-30 13:00
 * @Description:
 */
public class TxtLog implements ISysLog {
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyy-MM-dd");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("h:mm a");
    Date date = new Date();
    String destFilePath = "log/" + simpleDateFormat1.format(date) + ".txt";

    @Override
    public void info(String msg) {
        saveLogToTxt(msg, INFO);
    }

    @Override
    public void warn(String msg) {
        saveLogToTxt(msg, WARN);
    }

    @Override
    public void error(String msg) {
        saveLogToTxt(msg, ERROR);
    }

    public void saveLogToTxt(String msg, String type) {
        String log = new Log(msg, type, simpleDateFormat2.format(date)) + "\r\n";
        try {
            FileUtil.writeByBuffered(log, destFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
