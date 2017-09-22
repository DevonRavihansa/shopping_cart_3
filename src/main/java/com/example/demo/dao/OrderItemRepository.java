package com.example.demo.dao;

import com.example.demo.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Devon Ravihansa on 9/19/2017.
 */
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
