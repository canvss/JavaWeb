package com.canvs.book.dao.impl;

import com.canvs.book.dao.CartItemDAO;
import com.canvs.book.pojo.CartItem;
import com.canvs.book.pojo.User;
import com.canvs.ssm.basedao.BaseDAO;

import java.util.List;

public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public List<CartItem> getCartList(User user) {
        String sql = "SELECT * FROM t_cart_item WHERE userBean = ?";
        return super.getBeanList(sql,user.getId());
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        String sql = "INSERT INTO t_cart_item VALUES(0,?,?,?)";
        super.executeUpdate(sql,cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        String sql = "UPDATE t_cart_item SET buyCount=? WHERE id = ?";
        super.executeUpdate(sql,cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        String sql = "DELETE FROM t_cart_item WHERE id = ?";
        super.executeUpdate(sql,cartItem.getId());
    }
}
