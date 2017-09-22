package com.example.demo.dao;

import com.example.demo.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Devon Ravihansa on 9/10/2017.
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
    Product findBySku(String sku);
    Product findByNameOrPrice(String name,double price);
}
