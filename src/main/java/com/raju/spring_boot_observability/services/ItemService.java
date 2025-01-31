package com.raju.spring_boot_observability.services;

import com.raju.spring_boot_observability.entities.Item;
import com.raju.spring_boot_observability.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        log.info("Fetching all items from service");
        return itemRepository.findAll();
    }
}
