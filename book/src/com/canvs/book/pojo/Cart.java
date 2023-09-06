package com.canvs.book.pojo;


import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap; //购物车中购物车项的集合，key是book的id
    private Double totalMoney; //购物车的总金额
    private Integer totalCount; //购物车中的购物项的数量
    private Integer totalBookCount; //购物车中书本的总数量，不是购物车项的总数量

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemMap != null && !cartItemMap.isEmpty()) {
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> o : entries) {
                CartItem cartItem = cartItemMap.get(o.getKey());
                totalMoney += cartItem.getBook().getPrice() * cartItem.getBuyCount();
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        if (cartItemMap !=null){
            return cartItemMap.size();
        }
        return 0;
    }
    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap != null && !cartItemMap.isEmpty()){
            for (CartItem cartItem : cartItemMap.values()){
                totalBookCount += cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
