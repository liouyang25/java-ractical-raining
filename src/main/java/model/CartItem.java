package model;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-22 16:04
 * @Description:
 */
public class CartItem {
    private Goods product;
    private int quantity;

    public CartItem(Goods product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // 获取购物车条目总价
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
