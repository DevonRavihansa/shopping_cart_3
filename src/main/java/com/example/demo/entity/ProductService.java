package com.example.demo.entity;

import com.example.demo.dto.ProductDto;

/**
 * Created by Devon Ravihansa on 9/21/2017.
 */
public interface ProductService {
    ProductDto getProduct(String sku);
}
