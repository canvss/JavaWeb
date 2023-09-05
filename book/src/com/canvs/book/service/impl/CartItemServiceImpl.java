package com.canvs.book.service.impl;

import com.canvs.book.dao.CartItemDAO;
import com.canvs.book.pojo.Book;
import com.canvs.book.pojo.Cart;
import com.canvs.book.pojo.CartItem;
import com.canvs.book.pojo.User;
import com.canvs.book.service.BookService;
import com.canvs.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartList = getCartList(user);
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public List<CartItem> getCartList(User user) {
        List<CartItem> cartList = cartItemDAO.getCartList(user);
        for (CartItem cart : cartList) {
            Book book = bookService.getBookById(cart.getBook().getId());
            cart.setBook(book);
        }
        return cartList;
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        if (cart != null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null){
                cartItemMap = new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                updateCartItem(cartItemTemp);
            }else {
                addCartItem(cartItem);
            }
        }else {
            addCartItem(cartItem);
        }
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }
}
