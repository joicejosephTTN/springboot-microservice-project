package com.example.inventoryservice.service;

import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private  final InventoryRepository inventoryRepository;
    public Boolean isInStock(String skuCode){
        return inventoryRepository.findBySkuCode(skuCode).isPresent();

    }
}
