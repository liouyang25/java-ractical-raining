package view;

import model.Goods;

import java.util.*;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-11-30 08:38
 * @Description:
 */
public class Client {
    /**
     * 首页
     */
    public static final String INDEX = "I";
    /**
     * 用户
     */
    public static final String USER = "U";
    /**
     * 登陆
     */
    public static final String LOGIN = "L";
    /**
     * 退出
     */
    public static final String EXIT = "E";
    /**
     * 购物车
     */
    public static final String CART = "C";
    /**
     * 上一次操作记录
     */
    public static String HISTORY = "I";
    /**
     * 添加购物车
     */
    private static final String ADD = "A";

    public static final String ORDER = "O";

    Scanner scanner = new Scanner(System.in);

    Map<String, Goods> code2Goods = new HashMap<>();

    protected static Goods currentGoods;

    public void start() {
        GoodsClient goodsClient = new GoodsClient();
        UserClient userClient = new UserClient();
        CartClient cartClient = new CartClient();
        OrderClient orderClient = new OrderClient();

        String result = goodsClient.index();
        while (true) {
            switch (result) {
                case INDEX:
                    HISTORY = INDEX;
                    result = goodsClient.index();
                    break;
                case EXIT:
                    System.exit(0);
                case USER:
                    HISTORY = USER;
                    result = userClient.index();
                    break;
                case LOGIN:
                    result = userClient.showLogin();
                    break;
                case CART:
                    HISTORY = CART;
                    result = cartClient.index();
                    break;
                case ADD:
                    HISTORY = ADD;
                    result = cartClient.addCart();
                    break;
                case ORDER:
                    HISTORY = ORDER;
                    result = orderClient.index();
                    break;
                default:
                    System.out.println("Client出错了");
                    break;
            }
        }
    }

    public String userOperate(String msg, String... options) {
        String optionsStr = Arrays.toString(options);
        optionsStr = optionsStr.substring(1, optionsStr.length() - 1);
        msg = msg + " (" + optionsStr + ", E退出): ";
        System.out.println(msg);
        return scanner.next().trim().toUpperCase();
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.start();
    }
}
