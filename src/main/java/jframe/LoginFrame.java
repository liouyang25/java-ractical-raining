package jframe;

import model.common.Msg;
import utils.JSONUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author li
 */
public class LoginFrame extends JFrame implements BaseFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
    }

    public LoginFrame(JFrame jFrame) {
        super("登录/注册");

        // 创建卡片布局
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 添加登录页面
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "Login");

        // 设置窗口布局管理器
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭用户中心页面时不退出整个应用程序
        setSize(800, 500);
        setLocationRelativeTo(null); // 将窗口置于屏幕中央
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("用户名:");
        JLabel passwordLabel = new JLabel("密码:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");

        // 添加用户名标签和文本框
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(createStyledTextField(usernameField), gbc);

        // 添加密码标签和文本框
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(createStyledPasswordField(passwordField), gbc);

        // 添加登录按钮
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        // 添加注册按钮
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(registerButton, gbc);

        // 添加登录按钮的事件处理
        loginButton.addActionListener(e -> performLogin());

        // 添加注册按钮的事件处理
        registerButton.addActionListener(e -> showRegisterFramePage());

        return loginPanel;
    }

    private JTextField createStyledTextField(JTextField textField) {
        textField.setBorder(new LineBorder(Color.BLACK, 1)); // 设置边框
        textField.setFont(new Font("Arial", Font.PLAIN, 14)); // 设置字体
        return textField;
    }

    private JPasswordField createStyledPasswordField(JPasswordField passwordField) {
        passwordField.setBorder(new LineBorder(Color.BLACK, 1)); // 设置边框
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); // 设置字体
        return passwordField;
    }

    private void performLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
//        String result = userController.login(username,new String(password));
        String result = userController.login("admin", "123456");
        Msg msg = JSONUtil.JSON2Entity(result, Msg.class);
        if (msg.getType().equals(Msg.SUCCESS)) {
            JOptionPane.showMessageDialog(this, msg.getMsg(), "提示", JOptionPane.INFORMATION_MESSAGE);
            openGoodsFramePage();
        } else {
            JOptionPane.showMessageDialog(this, msg.getMsg(), "提示", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    private void showRegisterFramePage() {
        RegisterFrame registerFrame = new RegisterFrame(this);

        // 添加窗口关闭监听器
        registerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                // 用户中心页面关闭时记录商品页面的位置
                Point productListLocation = registerFrame.getLocation();
                // 商品页面出现在用户中心页面关闭的位置
                setLocation(productListLocation);
                setVisible(true);
            }
        });

        registerFrame.setVisible(true);

        // 隐藏商品页面
        setVisible(false);
    }

    private void openGoodsFramePage() {
        GoodsFrame goodsFrame = new GoodsFrame();
        goodsFrame.setVisible(true);

        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
