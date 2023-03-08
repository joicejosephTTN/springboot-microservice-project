package com.example.productservice.mapper;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    // we did not create an implementation class for our ProductMapper interface
    // because MapStruct creates it for us during compilation time.
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductRequestDTO mapToProductRequestDTO(Product entity);
    @Mapping(target = "id", ignore = true)
    Product mapToProduct(ProductRequestDTO dto);

    ProductResponseDTO mapToProductResponseDTO(Product entity);
}
