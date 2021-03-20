package com.example.jpa.shop.model;

import com.example.jpa.shop.model.dto.CartDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    // NOTE
    /* Each operation performed on the cart will be
    cascaded to the items */
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "cart_id")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public static Cart from(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setName(cartDto.getName());
        return cart;
    }
}
