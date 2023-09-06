package com.canvs.book.service;

import com.canvs.book.pojo.Book;
import com.canvs.book.pojo.Cart;
import com.canvs.book.pojo.CartItem;
import com.canvs.book.pojo.User;

import java.util.List;

public interface CartItemService {
    Cart getCart(User user);
    List<CartItem> getCartList(User user);
    void addOrUpdateCartItem(CartItem cartItem,Cart cart);
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void deleteCartItem(CartItem cartItem);
}
