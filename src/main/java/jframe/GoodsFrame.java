package jframe;

import controller.GoodsController;
import controller.UserController;
import model.Goods;
import model.common.Msg;
import utils.JSONUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

/**
 * @author li
 */
public class GoodsFrame extends JFrame implements BaseFrame {
    private Point productListLocation;
    private final DefaultTableModel tableModel;
    private final JTextField productIdField;
    private final Vector<Goods> products;

    public GoodsFrame() {
        // 设置窗口标题
        super("首页/商品页面");

        // 创建表格模型
        tableModel = new DefaultTableModel();
        tableModel.addColumn("商品编号");
        tableModel.addColumn("商品名称");
        tableModel.addColumn("单价");
        tableModel.addColumn("商品库存");

        // 创建表格
        JTable productListTable = new JTable(tableModel);


        productListTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(productListTable);

        add(scrollPane, BorderLayout.CENTER);

        // 创建添加商品到购物车的输入字段和按钮
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("商品编号:"));
        productIdField = new JTextField();
        inputPanel.add(productIdField);

        JButton addToCartButton = new JButton("添加到购物车");
        addToCartButton.addActionListener(e -> addToCart());
        inputPanel.add(addToCartButton);

        JButton showCartButton = new JButton("查看购物车");
        showCartButton.addActionListener(e -> showCartFramePage());
        inputPanel.add(showCartButton);


        add(inputPanel, BorderLayout.SOUTH);


        products = new Vector<>();
        addProducts(products);

        for (Goods g : products) {
            addProductToTable(g);
        }


        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);


        createMenuBar();
    }

    private void addProducts(Vector<Goods> products) {
        GoodsController goodsController = new GoodsController();
        String goodsListJson = goodsController.getGoodsList();
        Msg msgObj = JSONUtil.JSON2Entity(goodsListJson, Msg.class);
        Object obj = msgObj.getObj();
        List<?> goodsListObj = (List<?>) obj;
        int index = 1;
        for (Object o : goodsListObj) {
            String goodsJson = o.toString();
            Goods goods = JSONUtil.JSON2Entity(goodsJson, Goods.class);
            String productId = index + "";
            String productName = goods.getName();
            double price = goods.getPrice();
            int num = goods.getNumber();
            Goods goods1 = new Goods();
            goods1.setId(productId);
            goods1.setName(productName);
            goods1.setPrice(price);
            goods1.setNumber(num);
            products.add(goods1);
            index++;
        }
    }

    private void addProductToTable(Goods product) {
        Vector<Object> row = new Vector<>();
        row.add(product.getId());
        row.add(product.getName());
        row.add(product.getPrice());
        row.add(product.getNumber());
        tableModel.addRow(row);
    }

    private void addToCart() {
//        if (new UserController().getLoginUser() == null) {
//            JOptionPane.showMessageDialog(this, "还没有登陆", "提示", JOptionPane.INFORMATION_MESSAGE);
//            showLoginFramePage();
//            return;
//        }
        String productId = productIdField.getText();


        Goods selectedProduct = findProductById(productId);

        if (selectedProduct != null) {
            String status = cartController.addCart(selectedProduct);
            Msg msg = JSONUtil.JSON2Entity(status, Msg.class);
            if (msg.getType().equals(Msg.SUCCESS)) {
                JOptionPane.showMessageDialog(this, msg.getMsg(), "信息", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "未找到商品编号为 " + productId + " 的商品", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

        productIdField.setText("");
    }

    /**
     * 根据商品编号查找商品
     *
     * @param productId 商品的id
     * @return Product类
     */
    private Goods findProductById(String productId) {
        String name = null;
        double price = 0;
        for (Goods product : products) {
            if (product.getId().equals(productId)) {
                name = product.getName();
                price = product.getPrice();
            }
        }
        GoodsController goodsController = new GoodsController();
        String goodsListJson = goodsController.getGoodsList();
        Msg msgObj = JSONUtil.JSON2Entity(goodsListJson, Msg.class);
        Object obj = msgObj.getObj();
        List<?> goodsListObj = (List<?>) obj;

        for (Object o : goodsListObj) {
            String goodsJson = o.toString();
            Goods goods = JSONUtil.JSON2Entity(goodsJson, Goods.class);
            if (name.equals(goods.getName()) && price == goods.getPrice()) {
                return goods;
            }
        }
        return null;
    }

    /**
     * 创建顶部导航栏
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem indexItem = new JMenuItem("首页/商品页面");

        JMenuItem loginItem = new JMenuItem("登陆");
        loginItem.addActionListener(e -> showLoginFramePage());

        JMenuItem registerItem = new JMenuItem("注册");
        registerItem.addActionListener(e -> showRegisterFramePage());

        menuBar.add(indexItem);
        menuBar.add(loginItem);
        menuBar.add(registerItem);

        // 设置窗口的菜单栏
        setJMenuBar(menuBar);
    }

    /**
     * 显示用户登陆页面
     */
    private void showLoginFramePage() {
        LoginFrame loginFrame = new LoginFrame(this);

        // 添加窗口关闭监听器
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                // 用户中心页面关闭时记录商品页面的位置
                productListLocation = loginFrame.getLocation();
                // 商品页面出现在用户中心页面关闭的位置
                setLocation(productListLocation);
                setVisible(true);
            }
        });

        loginFrame.setVisible(true);

        // 隐藏商品页面
        setVisible(false);
    }

    /**
     * 显示用户登陆页面
     */
    private void showRegisterFramePage() {
        RegisterFrame registerFrame = new RegisterFrame(this);

        // 添加窗口关闭监听器
        registerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                // 用户中心页面关闭时记录商品页面的位置
                productListLocation = registerFrame.getLocation();
                // 商品页面出现在用户中心页面关闭的位置
                setLocation(productListLocation);
                setVisible(true);
            }
        });

        registerFrame.setVisible(true);

        // 隐藏商品页面
        setVisible(false);
    }

    private void showCartFramePage() {
        CartFrame cartFrame = new CartFrame(this);

        // 添加窗口关闭监听器
        cartFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                // 用户中心页面关闭时记录商品页面的位置
                productListLocation = cartFrame.getLocation();
                // 商品页面出现在用户中心页面关闭的位置
                setLocation(productListLocation);
                setVisible(true);
            }
        });

        cartFrame.setVisible(true);

        // 隐藏商品页面
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GoodsFrame goodsFrame = new GoodsFrame();
            goodsFrame.setVisible(true);
        });
    }
}
