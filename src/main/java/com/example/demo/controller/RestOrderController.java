package com.example.demo.controller;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.*;
import com.example.demo.model.Cart;
import com.example.demo.model.CartManager;
import com.example.demo.service.Views;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 9/19/2017.
 */
@RestController
@RequestMapping("/rest/order")
public class RestOrderController {

    @Autowired
    private CartManager cartManager;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/{poNumber}")
    public Order getOrder(@PathVariable("poNumber")String poNumber){
        return orderService.getOrder(poNumber);
    }

    @RequestMapping("/product/{id}")
    public Product getProduct(@PathVariable("id")Product product){
        return product;
    }

    @RequestMapping("/product/add/{oid}/{pid}")
    public Order addItem(@PathVariable("pid")Product product, @PathVariable("oid")Order order){
        orderService.addItem(order,product,1);
        return order;
    }

    @RequestMapping("/cart")
    @JsonView(Views.AdminView.class)
    public Cart cart(HttpSession session, Authentication authentication){
        if(authentication != null){
            User user = userRepository.findByUsername(authentication.getName());
            Order order = orderService.getOrder(user);
            Cart cart = new Cart();
            for(OrderItem orderItem : order.getOrderItems()){
                cart.addItem(orderItem.getProduct(), orderItem.getQuantity());
            }
            return cart;
        }
        return cartManager.getCart(session);
    }

    @JsonView(Views.UserView.class)
    @RequestMapping("/order")
    public Order order(Authentication authentication){
        if(authentication != null){
            User user = userRepository.findByUsername(authentication.getName());
            Order order = orderService.getOrder(user);
            return order;
        }
        return null;
    }

    @RequestMapping("items/{oid}")
    public void items(@PathVariable("oid")String oid){
        for(OrderItem item :  orderService.getOrder(oid).getOrderItems()){
            System.out.println(item.getProduct().getName());
        }
    }
}
