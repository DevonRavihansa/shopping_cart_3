package com.example.demo.entity;

import com.example.demo.dao.OrderItemRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.OrderStatusRepository;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Devon Ravihansa on 9/19/2017.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderItem getItem(Order order, Product product){
        for(OrderItem item : order.getOrderItems()){
            if(item.getProduct().getId() == product.getId()){
                return item;
            }
        }
        return null;
    }

    @Override
    public Order getOrder(String poNumber) {
        return orderRepository.findByPoNumber(poNumber);
    }

    @Override
    public Order getOrder(User user) {
        OrderStatus status = orderStatusRepository.findByStatusCode("INCOMPLETE");
        return orderRepository.findByUserAndStatus(user, status);
    }

    public Cart getOrderAsCart(User user){
        OrderStatus status = orderStatusRepository.findByStatusCode("INCOMPLETE");
        Order order = orderRepository.findByUserAndStatus(user, status);
        Cart cart = new Cart();
        for(OrderItem orderItem : order.getOrderItems()){
            cart.addItem(orderItem.getProduct(), orderItem.getQuantity());
        }
        return cart;
    }

    @Override
    public void addItem(Order order, Product product, int quantity) {
        OrderItem item = getItem(order, product);

        if(item != null){
            item.setQuantity(item.getQuantity() + quantity);
        }else{
            item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(quantity);
        }
        orderItemRepository.save(item);
    }

    @Override
    public void updateItem(Order order, Product product, int quantity) {
        OrderItem item = getItem(order, product);

        if(item != null){
            item.setQuantity(quantity);
            orderItemRepository.save(item);
        }else{
            // throw new ProductNotFoundException();
        }
    }

    @Override
    public void removeItem(Order order, Product product) {
        OrderItem item = getItem(order, product);

        if(item != null){
            orderItemRepository.delete(item);
        }else{
            // throw new ProductNotFoundException();
        }
    }

    @Override
    public void appendCart(User user, Cart cart) {
        Order order = getOrder(user);

        for(CartItem cartItem : cart.getItems()){
            OrderItem orderItem = getItem(order, cartItem.getProduct());

            if(orderItem != null){
                updateItem(order,cartItem.getProduct(),cartItem.getQuantity());
            }else{
                addItem(order, cartItem.getProduct(), cartItem.getQuantity());
            }
        }
    }
}
