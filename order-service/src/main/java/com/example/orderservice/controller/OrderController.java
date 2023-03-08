package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.ResponseDTO;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    @PostMapping(value = "/")
    public ResponseDTO<Objects> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.placeOrder(orderRequestDTO);
    }
}
