package com.example.inventoryservice.controller;

import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping(value = "/{skuCode}")
    public Boolean isInStock(@PathVariable("skuCode") String skuCode){
        return inventoryService.isInStock(skuCode);
    }

}
