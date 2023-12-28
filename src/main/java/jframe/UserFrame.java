package jframe;

import javax.swing.*;
import java.awt.*;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-20 00:17
 * @Description:
 */
public class UserFrame extends JFrame {

    public UserFrame(JFrame parent) {
        super("用户中心");

        // 设置用户中心页面属性
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭用户中心页面时不退出整个应用程序
        setLocationRelativeTo(parent);  // 居中显示在父窗口中

        // 添加顶部导航栏
        createMenuBar();

        // 创建按钮用于前往登陆、注册和更新账号或密码
        JButton goToLoginButton = new JButton("前往登录");
        JButton goToRegisterButton = new JButton("前往注册");
        JButton updateAccountButton = new JButton("更新账号或密码");

        // 添加按钮的点击事件监听器
        goToLoginButton.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame(this);
            loginFrame.setVisible(true);

            setVisible(false);
        });

        goToRegisterButton.addActionListener(e -> {
            dispose();  // 关闭用户中心页面
            openRegisterPage();  // 打开注册页面
        });

        updateAccountButton.addActionListener(e -> {
            // 在这里添加更新账号或密码的逻辑
            // 可以是弹出对话框、打开新页面等
            JOptionPane.showMessageDialog(UserFrame.this, "更新账号或密码的逻辑");
        });

        // 创建面板并添加按钮
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(goToLoginButton);
        panel.add(goToRegisterButton);
        panel.add(updateAccountButton);

        // 添加面板到用户中心页面
        add(panel);
    }

    /**
     * 创建顶部导航栏
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem indexItem = new JMenuItem("首页/商品页面");
        indexItem.addActionListener(e -> showGoodsFrame());
        // 用户中心菜单
        JMenuItem userCenterItem = new JMenuItem("用户中心");

        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(e -> System.exit(0));

        menuBar.add(indexItem);
        menuBar.add(userCenterItem);
        menuBar.add(exitItem);

        // 设置窗口的菜单栏
        setJMenuBar(menuBar);
    }

    private void showGoodsFrame() {
        GoodsFrame goodsFrame = new GoodsFrame();
        Point location = getLocation();
        goodsFrame.setLocation(location);
        goodsFrame.setVisible(true);
        dispose();
    }

    // 打开登陆页面
    private void openLoginPage() {
        LoginFrame loginFrame = new LoginFrame(this);
        loginFrame.setVisible(true);
    }

    // 打开注册页面
    private void openRegisterPage() {
        // 在这里添加打开注册页面的代码
        // 可以是新窗口、新页面等
        RegisterFrame registerFrame = new RegisterFrame(this);
        registerFrame.setVisible(true);
    }
}



