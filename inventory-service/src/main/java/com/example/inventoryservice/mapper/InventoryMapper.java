package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.InventoryResponseDTO;
import com.example.inventoryservice.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper MAPPER = Mappers.getMapper(InventoryMapper.class);

    InventoryResponseDTO mapToInventoryResponseDTO(Inventory entity);
}
