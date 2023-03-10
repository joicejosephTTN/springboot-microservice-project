package com.example.orderservice.dto;

public record InventoryResponseDTO(
        String skuCode,
        Integer quantity
) {}
