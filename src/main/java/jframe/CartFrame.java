package jframe;

import controller.CartController;
import controller.OrderController;
import controller.UserController;
import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.CartGoods;
import model.Goods;
import model.Order;
import model.ShoppingCart;
import model.common.Msg;
import utils.JSONUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-21 10:15
 * @Description:
 */
public class CartFrame extends JFrame implements BaseFrame {
    private final DefaultTableModel tableModel;
    private final JLabel totalPriceLabel;
    private double total;
    JTable productListTable;

    public CartFrame(JFrame parent) {
        super("购物车");

        // 创建表格模型
        tableModel = new DefaultTableModel();
        tableModel.addColumn("商品编号");
        tableModel.addColumn("商品名称");
        tableModel.addColumn("单价");
        tableModel.addColumn("商品库存");

        // 创建表格
        productListTable = new JTable(tableModel);

        // 创建购物车和购物车总价标签
        totalPriceLabel = new JLabel("购物车总价：￥0.00");

        // 添加购物车总价标签到窗口底部
        add(totalPriceLabel, BorderLayout.SOUTH);

        // 创建提交订单按钮
        JButton submitOrderButton = new JButton("提交订单");
        submitOrderButton.addActionListener(e -> submitOrder());

        // 使用GridLayout来将总价和提交按钮放在一行两列的布局中
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(totalPriceLabel);

        // 使用FlowLayout来让按钮靠右显示
        JPanel submitButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitButtonPanel.add(submitOrderButton);
        bottomPanel.add(submitButtonPanel);

        // 添加底部面板到窗口底部
        add(bottomPanel, BorderLayout.SOUTH);


        productListTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(productListTable);

        add(scrollPane, BorderLayout.CENTER);

        // 设置用户中心页面属性
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭用户中心页面时不退出整个应用程序
        setLocationRelativeTo(parent);  // 居中显示在父窗口中

        // 添加顶部导航栏
        createMenuBar();

        Vector<Goods> products = new Vector<>();
        addCartGoods(products);

        for (Goods g : products) {
            addProductToTable(g);
        }

    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem indexItem = new JMenuItem("首页/商品页面");
        indexItem.addActionListener(e -> showGoodsFrame());
        // 用户中心菜单
        JMenuItem userCenterItem = new JMenuItem("用户中心");
        userCenterItem.addActionListener(e -> showUserCenterFrame());

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

    private void addProductToTable(Goods product) {
        Vector<Object> row = new Vector<>();
        row.add(product.getId());
        row.add(product.getName());
        row.add(product.getPrice());
        row.add(product.getNumber());
        tableModel.addRow(row);
    }

    private void addCartGoods(Vector<Goods> products) {
        String msg = cartController.showCart();
        Msg msgObj = JSONUtil.JSON2Entity(msg, Msg.class);
        Object obj = msgObj.getObj();
        List<?> goodsListObj = (List<?>) obj;
        int index = 1;
        for (Object o : goodsListObj) {
            String goodsJson = o.toString();
            CartGoods cartGoods = JSONUtil.JSON2Entity(goodsJson, CartGoods.class);
            String productId = index + "";
            int num = cartGoods.getGoodsNum(); //数量
            double price = cartGoods.getPrice(); //单价
            String productName = cartGoods.getName() + ""; //商品名称
            Goods goods1 = new Goods();
            goods1.setId(productId);
            goods1.setName(productName);
            goods1.setPrice(price);
            goods1.setNumber(num);
            products.add(goods1);
            //计算总金额
            total += cartGoods.getPrice() * cartGoods.getGoodsNum();
            updateTotalPriceLabel(0);
            index++;
        }
    }

    // 更新购物车总价标签的方法
    private void updateTotalPriceLabel(int i) {
        if (i == 1) {
            totalPriceLabel.setText("购物车总价：￥" + String.format("%.2f", 0.0));
        } else {
            totalPriceLabel.setText("购物车总价：￥" + String.format("%.2f", total));
        }
    }

    // 提交订单的方法
    private void submitOrder() {
        String contactPerson = JOptionPane.showInputDialog(this, "请输入联系人姓名:");
        String shippingAddress = JOptionPane.showInputDialog(this, "请输入收货地址:");
        String contactNumber = JOptionPane.showInputDialog(this, "请输入联系电话:");

        // 示例：创建订单信息
        String orderInfo = "联系人：" + contactPerson + "\n"
                + "收货地址：" + shippingAddress + "\n"
                + "联系电话：" + contactNumber;

        // 显示订单信息
        JOptionPane.showMessageDialog(this, orderInfo, "订单信息", JOptionPane.INFORMATION_MESSAGE);

        clearProductTable();
        updateTotalPriceLabel(1);

        orderController.createOrder(contactPerson, shippingAddress, contactNumber);
        pay();
    }

    // 清空商品列表表格
    private void clearProductTable() {
        // 移除所有行
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
        // 刷新表格
        productListTable.repaint();
    }

    private void showUserCenterFrame() {
        UserFrame userFrame = new UserFrame(this);

        // 添加窗口关闭监听器
        userFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                setVisible(true);
            }
        });

        userFrame.setVisible(true);

        // 隐藏商品页面
        setVisible(false);
    }

    private void pay() {
        Map<String, Integer> goodsCart = CartController.getCart();
        Msg msg = new Msg();
        String status;
        while (true) {
            String money = JOptionPane.showInputDialog(this, "请输入支付金额: ");
            OrderDAO orderDAO = new OrderDAOImpl();
            Order orders;
            orders = orderDAO.getOrderById(OrderController.id);
            // 支付金额
            double amount = orders.getAmount();
            // 获取库存数量
            for (Map.Entry<String, Integer> entry : goodsCart.entrySet()) {
                //根据id获取数据
                Goods goods = goodsController.getGoodsById1(entry.getKey());
                // 库存
                int num = goods.getNumber();
                // 剩余
                num -= entry.getValue();
                goods.setNumber(num);
                System.out.println(goods.getNumber() >= 0 && Double.parseDouble(money) > amount);
                if (goods.getNumber() >= 0 && Double.parseDouble(money) > amount) {
                    // 更新goods表
                    status = goodsController.updateGoodsById(entry.getKey(), goods);
                    //解析msg
                    msg = JSONUtil.JSON2Entity(status, Msg.class);
                    // 更新order表
                    UserController userController = new UserController();
                    orders.setUserId(userController.getLoginUser().getId());
                    orders.setState("已支付");
                    orderDAO.updateOrderById(OrderController.id, orders);
                } else {
                    msg.setType(Msg.FAIL);
                }
            }
            System.out.println(msg.getType());
            if (msg.getType().equals(Msg.SUCCESS)) {
                JOptionPane.showMessageDialog(this, "支付成功!", "信息", JOptionPane.INFORMATION_MESSAGE);
                // 清空
                CartController.getCart().clear();
                Order order = orderController.getOrder();
                order.setState("已支付");
                orderController.setOrder(order);
                break;
            } else {
                String str = JOptionPane.showInputDialog(this, "商品库存不足或支付金额不足, 是否重新支付？(Y/N): ");
                if ("N".equalsIgnoreCase(str.trim())) {
                    break;
                }
            }
        }
    }

}
