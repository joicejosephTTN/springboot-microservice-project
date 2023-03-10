package com.example.orderservice.feignclient;

import com.example.orderservice.dto.InventoryResponseDTO;
import com.example.orderservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:9093/api/inventory")
public interface InventoryServiceClient {

    @GetMapping("/")
    ResponseDTO<List<InventoryResponseDTO>> isInStock(@RequestParam List<String> skuCodes);
}
