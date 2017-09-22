package com.example.demo.entity;

import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Devon Ravihansa on 9/21/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto getProduct(String sku) {
        Product product = productRepository.findBySku(sku);
        return modelMapper.map(product, ProductDto.class);
    }
}
