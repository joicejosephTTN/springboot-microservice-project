package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.dto.ResponseDTO;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<Object> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.createProduct(productRequestDTO);
    }
}
