package com.raju.spring_boot_observability.controllers;

import com.raju.spring_boot_observability.entities.Item;
import com.raju.spring_boot_observability.services.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        log.info("Fetching all items from controller");
        return itemService.getAllItems();
    }
}
