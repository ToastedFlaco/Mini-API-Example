package com.example.jpa.shop.service;

import com.example.jpa.shop.model.Cart;

import java.util.List;

public interface CartService {
    public Cart addCart(Cart cart);
    public List<Cart> getCarts();
    public Cart getCart(Long id);
    public Cart editCart(Long id, Cart cart);
    public Cart deleteCart(Long id);
    public Cart addItemToCart(Long cartId, Long itemId);
    public Cart removeItemFromCart(Long cartId, Long itemId);
}
