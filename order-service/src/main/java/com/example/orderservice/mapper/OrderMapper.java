package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderLineItemsDTO;
import com.example.orderservice.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderLineItemsDTO mapToOrderLineItemsDTO(OrderLineItems entity);
    OrderLineItems mapToOrderLineItems(OrderLineItemsDTO dto);

}
