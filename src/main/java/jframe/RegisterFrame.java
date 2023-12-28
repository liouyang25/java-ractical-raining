package jframe;

import model.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author li
 */
public class RegisterFrame extends JFrame implements BaseFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton backButton;

    public RegisterFrame(JFrame jFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("新用户名:");
        JLabel passwordLabel = new JLabel("新密码:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton registerButton = new JButton("注册");
        backButton = new JButton("返回");

        // 添加新用户名标签和文本框
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(createStyledTextField(usernameField), gbc);

        // 添加新密码标签和文本框
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(createStyledPasswordField(passwordField), gbc);

        // 添加注册按钮
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        // 添加返回按钮
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);

        // 添加注册按钮的事件处理
        registerButton.addActionListener(e -> performRegistration());

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭用户中心页面时不退出整个应用程序
        setSize(800, 500);
        setLocationRelativeTo(null); // 将窗口置于屏幕中央
        setVisible(true);
    }

    private JTextField createStyledTextField(JTextField textField) {
        textField.setBorder(new LineBorder(Color.BLACK, 1));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JPasswordField createStyledPasswordField(JPasswordField passwordField) {
        passwordField.setBorder(new LineBorder(Color.BLACK, 1));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        return passwordField;
    }

    private void performRegistration() {
        String newUsername = usernameField.getText();
        char[] newPassword = passwordField.getPassword();

        if (newUsername.isEmpty() || newPassword.length < 6) {
            JOptionPane.showMessageDialog(this, "用户名不能为空, 密码至少为6位", "提示", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userController.setUserInfo(newUsername, new String(newPassword));
        int affectedRows = userController.addUser(user);
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(this, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            showLoginFramePage();
        } else {
            JOptionPane.showMessageDialog(this, "注册失败", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showLoginFramePage() {
        setVisible(false);
    }
}
