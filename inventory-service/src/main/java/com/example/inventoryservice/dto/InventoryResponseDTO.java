package com.example.inventoryservice.dto;

public record InventoryResponseDTO(
        String skuCode,
        Integer quantity
) {}
