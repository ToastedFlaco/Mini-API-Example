package com.example.jpa.shop.controller;

import com.example.jpa.shop.model.Cart;
import com.example.jpa.shop.model.dto.CartDto;
import com.example.jpa.shop.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @PostMapping("")
    public ResponseEntity<CartDto> addCart(@RequestBody final CartDto cartDto) {
        Cart cart = cartService.addCart(Cart.from(cartDto));
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartService.getCarts();
        List<CartDto> cartsDto = carts.stream().map(CartDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(cartsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable final Long id) {
        Cart cart = cartService.getCart(id);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> editCart(@PathVariable final Long id, @RequestBody final CartDto cartDto) {
        Cart editedCart = cartService.editCart(id, Cart.from(cartDto));
        return new ResponseEntity<>(CartDto.from(editedCart), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable final Long id, @RequestBody final CartDto cartDto) {
        Cart cart = cartService.editCart(id, Cart.from(cartDto));
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @PostMapping("/{cartId}/items/{itemId}/add")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable final Long cartId, @PathVariable final Long itemId) {
        Cart cart = cartService.addItemToCart(cartId, itemId);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/items/{itemId}/remove")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable final Long cartId,
                                                      @PathVariable final Long itemId) {
        Cart cart = cartService.removeItemFromCart(cartId, itemId);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

}
