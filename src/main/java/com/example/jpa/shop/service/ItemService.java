package com.example.jpa.shop.service;

import com.example.jpa.shop.model.Item;

import java.util.List;

public interface ItemService {
    public Item addItem(Item item);
    public List<Item> getItems();
    public Item getItem(Long id);
    public Item editItem(Long id, Item item);
    public Item deleteItem(Long id);
}
