package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Devon Ravihansa on 9/14/2017.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByPoNumber(String poNumber);
    List<Order> findByUser(User user);
    Order findByUserAndStatus(User user, OrderStatus status);
    List<Order> findByStatus(OrderStatus status);
}
