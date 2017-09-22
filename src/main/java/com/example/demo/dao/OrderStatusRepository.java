package com.example.demo.dao;

import com.example.demo.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Devon Ravihansa on 9/15/2017.
 */
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    OrderStatus findByStatusCode(String statusCode);
}
