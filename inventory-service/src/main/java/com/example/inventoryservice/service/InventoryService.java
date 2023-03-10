package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponseDTO;
import com.example.inventoryservice.dto.ResponseDTO;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private  final InventoryRepository inventoryRepository;
    public ResponseDTO<List<InventoryResponseDTO>> isInStock(List<String> skuCodes){
        ResponseDTO<List<InventoryResponseDTO>> responseDTO = new ResponseDTO<>(Boolean.TRUE,"REQUEST_PROCESSED");

        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes);
        if(CollectionUtils.isEmpty(inventoryList)){
            return new ResponseDTO<>(Boolean.FALSE, "NOT_FOUND");
        }
        List<InventoryResponseDTO> resultList = inventoryList.stream()
                .map(InventoryMapper.MAPPER::mapToInventoryResponseDTO)
                .filter(inventoryResponseDTO -> inventoryResponseDTO.quantity() > 0)
                .toList();

        if(CollectionUtils.isEmpty(resultList)){
            return new ResponseDTO<>(Boolean.FALSE, "NOT_FOUND");
        }
        responseDTO.setData(resultList);
        return responseDTO;
    }
}
