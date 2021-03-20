package com.example.jpa.shop.service;

import com.example.jpa.shop.exception.ItemNotFoundException;
import com.example.jpa.shop.model.Item;
import com.example.jpa.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    // NOTE
    // There are two forms of dependency injection

    // #1 - Direct Injection
    @Autowired
    ItemRepository itemRepository;

    // #2 - Constructor Injection
    /*
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    */

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    // NOTE
    // There are two forms of doing this method

    // #1 - By cast of List<Item>
    public List<Item> getItems() {
        return (List<Item>) itemRepository.findAll();
    }

    // #2 - Converting to List
    /*         return StreamSupport
                .stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()); */

    @Override
    public Item getItem(Long id) {
        // NOTE
        // We're using exception handling and we create
        // a new exception called CartNotFoundException
        return itemRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(id));
    }

    @Override
    public Item editItem(Long id, Item item) {
        Item itemEdit = getItem(id);
        itemEdit.setSerialNumber(item.getSerialNumber());
        return itemEdit;
    }

    @Override
    public Item deleteItem(Long id) {
        Item item = getItem(id);
        itemRepository.delete(item);
        return item;
    }
}
