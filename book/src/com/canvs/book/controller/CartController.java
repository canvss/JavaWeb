package com.canvs.book.controller;

import com.canvs.book.pojo.Book;
import com.canvs.book.pojo.Cart;
import com.canvs.book.pojo.CartItem;
import com.canvs.book.pojo.User;
import com.canvs.book.service.CartItemService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartController {
    private CartItemService cartItemService;

    public String index(HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(currUser);
        currUser.setCart(cart);
        session.setAttribute("currUser", currUser);
        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,currUser);
        cartItemService.addOrUpdateCartItem(cartItem,currUser.getCart());
        return "redirect:cart.do";
    }
}
