package com.example.jpa.shop.service;

import com.example.jpa.shop.exception.CartNotFoundException;
import com.example.jpa.shop.exception.ItemIsAlreadyAssignedException;
import com.example.jpa.shop.model.Cart;
import com.example.jpa.shop.model.Item;
import com.example.jpa.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemServiceImpl itemService;

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow(() ->
                new CartNotFoundException(id));
    }

    @Override
    public Cart editCart(Long id, Cart cart) {
        Cart cartEdit = getCart(id);
        cartEdit.setName(cart.getName());
        return cartEdit;
    }

    @Override
    public Cart deleteCart(Long id) {
        Cart cart = getCart(id);
        cartRepository.delete(cart);
        return cart;
    }

    @Override
    public Cart addItemToCart(Long cartId, Long itemId) {
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        if(Objects.nonNull(item.getCart())) {
            throw new ItemIsAlreadyAssignedException(itemId, item.getCart().getId());
        }
        cart.addItem(item);
        item.setCart(cart);
        return cart;
    }

    @Override
    public Cart removeItemFromCart(Long cartId, Long itemId) {
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        cart.removeItem(item);
        return cart;
    }
}
