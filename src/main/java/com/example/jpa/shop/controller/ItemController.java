package com.example.jpa.shop.controller;

import com.example.jpa.shop.model.Item;
import com.example.jpa.shop.model.dto.ItemDto;
import com.example.jpa.shop.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;

    @PostMapping("")
    public ResponseEntity<ItemDto> addItem(@RequestBody final ItemDto itemDto) {
        Item item = itemService.addItem(Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ItemDto>> getItems() {
        List<Item> items = itemService.getItems();
        List<ItemDto> itemsDto = items.stream().map(ItemDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable final Long id) {
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> editItem(@PathVariable final Long id, @RequestBody final ItemDto itemDto) {
        Item editedItem = itemService.editItem(id, Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(editedItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDto> deleteItem(@PathVariable final Long id) {
        Item item = itemService.deleteItem(id);
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }


}
