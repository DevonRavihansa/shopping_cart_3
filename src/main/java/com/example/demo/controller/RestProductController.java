package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Devon Ravihansa on 9/21/2017.
 */
@RestController
@RequestMapping("/rest/product")
public class RestProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserDto userDto;

    @RequestMapping("/json")
    public ProductDto getProduct(@RequestParam("sku") String sku){
        return productService.getProduct(sku);
    }

    @RequestMapping("find")
    public ProductDto find(@Valid @RequestBody ProductDto productDto){
        return productService.getProduct(productDto.getSku());
    }
}
