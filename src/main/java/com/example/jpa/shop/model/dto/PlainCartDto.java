package com.example.jpa.shop.model.dto;

import com.example.jpa.shop.model.Cart;
import lombok.Data;
// This is for prevent an infinite loop when showing
// Items that are not in the cart
@Data
public class PlainCartDto {
    private Long id;
    private String name;

    public static PlainCartDto from(Cart cart) {
        PlainCartDto plainCartDto = new PlainCartDto();
        plainCartDto.setId(cart.getId());
        plainCartDto.setName(cart.getName());
        return plainCartDto;
    }
}
