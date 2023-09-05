package com.canvs.book.dao;

import com.canvs.book.pojo.CartItem;
import com.canvs.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    //获取指定用户的购物车信息
    List<CartItem> getCartList(User user);
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
}
