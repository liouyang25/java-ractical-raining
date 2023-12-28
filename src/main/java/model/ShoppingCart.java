package model;

import java.util.Vector;

/**
 * @author: 李卫
 * @email: liouyang25@gmail.com
 * @create: 2023-12-22 16:05
 * @Description:
 */
public class ShoppingCart {
    private final Vector<CartItem> cartItems;

    public ShoppingCart() {
        cartItems = new Vector<>();
    }

    public void addProduct(Goods product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
    }

    // 计算购物车总价
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }

    // 清空购物车
    public void clear() {
        cartItems.clear();
    }
}
