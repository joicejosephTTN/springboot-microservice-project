package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.ResponseDTO;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public ResponseDTO<Objects> placeOrder(OrderRequestDTO orderRequestDTO){
        ResponseDTO<Objects> responseDTO = new ResponseDTO<>(Boolean.TRUE,"REQUEST_PROCESSED_SUCCESSFULLY");
        if(Objects.isNull(orderRequestDTO)){
            return new ResponseDTO<>(Boolean.FALSE,"INVALID_REQUEST");
        }
        Order newOrder = new Order();
        newOrder.setOrderNumber(UUID.randomUUID().toString());

        if(Objects.isNull(orderRequestDTO.getOrderLineItemsDtoList())){
            return new ResponseDTO<>(Boolean.FALSE,"INVALID_REQUEST");
        }

        List<OrderLineItems> orderLineItemsList = orderRequestDTO.getOrderLineItemsDtoList().stream()
                .map(OrderMapper.MAPPER::mapToOrderLineItems)
                .toList();

        newOrder.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(newOrder);
        return responseDTO;

    }
}
