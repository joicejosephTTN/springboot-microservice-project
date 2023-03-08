package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.dto.ResponseDTO;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseDTO<Object> createProduct(ProductRequestDTO productRequestDTO){
        log.info("-->createProduct");
        ResponseDTO<Object> responseDTO = new ResponseDTO<>(Boolean.TRUE,"REQUEST_PROCESSED_SUCCESFULLY");
        Product newProduct = ProductMapper.MAPPER.mapToProduct(productRequestDTO);
        productRepository.save(newProduct);
        log.info("<---createProduct. Product {} is created.", newProduct.getId());
        return responseDTO;
    }

    public ResponseDTO<Object> getAllProducts(){
        log.info("-->getAllProducts");
        ResponseDTO<Object> responseDTO = new ResponseDTO<>(Boolean.TRUE,
                "REQUEST_PROCESSED_SUCCESSFULLY");
        List<Product> products = productRepository.findAll();

        if(CollectionUtils.isEmpty(products)){
            return new ResponseDTO<>(Boolean.FALSE,"PRODUCT_NOT_FOUND");
        }

        List<ProductResponseDTO> productResponseDTOS = products.stream()
                .map(ProductMapper.MAPPER::mapToProductResponseDTO)
                .collect(Collectors.toList());
        responseDTO.setData(productResponseDTOS);
        log.info("<--getAllProducts");
        return responseDTO;
    }
}

