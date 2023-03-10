package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponseDTO;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.ResponseDTO;
import com.example.orderservice.feignclient.InventoryServiceClient;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    private  InventoryServiceClient inventoryServiceClient;

    public ResponseDTO<List<InventoryResponseDTO>> placeOrder(OrderRequestDTO orderRequestDTO){
        ResponseDTO<List<InventoryResponseDTO>> responseDTO = new ResponseDTO<>(
                Boolean.TRUE,
                "REQUEST_PROCESSED_SUCCESSFULLY"
        );

        if(Objects.isNull(orderRequestDTO)){
            return new ResponseDTO<>(Boolean.FALSE,"INVALID_REQUEST");
        }
        else if(Objects.isNull(orderRequestDTO.getOrderLineItemsDtoList())){
            return new ResponseDTO<>(Boolean.FALSE,"INVALID_REQUEST");
        }
        else {

            Order newOrder = new Order();
            newOrder.setOrderNumber(UUID.randomUUID().toString());

            List<OrderLineItems> orderLineItemsList = orderRequestDTO.getOrderLineItemsDtoList()
                    .stream()
                    .map(OrderMapper.MAPPER::mapToOrderLineItems)
                    .toList();

           List<InventoryResponseDTO> unavailableItems = itemsOutOfStock(orderLineItemsList);

            if (!CollectionUtils.isEmpty(unavailableItems)) {
                return new ResponseDTO<>(Boolean.FALSE, "OUT_OF_STOCK", unavailableItems);
            }

            newOrder.setOrderLineItemsList(orderLineItemsList);
            orderRepository.save(newOrder);
            return responseDTO;
        }
    }

    private List<InventoryResponseDTO> itemsOutOfStock(List<OrderLineItems> orderLineItemsList){
        List<String> skuCodes = orderLineItemsList.stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        ResponseDTO<List<InventoryResponseDTO>> orderedItems = inventoryServiceClient.isInStock(skuCodes);

        return orderedItems.getData().stream()
                .filter(item->!skuCodes.contains(item.skuCode()))
                .toList();
    }
}
