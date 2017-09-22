package com.example.demo.entity;

import com.example.demo.model.Cart;

/**
 * Created by Devon Ravihansa on 9/19/2017.
 */
public interface OrderService {
    Order getOrder(String poNumber);
    Order getOrder(User user);
    void addItem(Order order, Product product, int quantity);
    void updateItem(Order order, Product product, int quantity);
    void removeItem(Order order, Product product);
    void appendCart(User user, Cart cart);
    Cart getOrderAsCart(User user);
}
