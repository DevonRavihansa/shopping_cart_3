package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderService;
import com.example.demo.entity.User;
import com.example.demo.model.Cart;
import com.example.demo.model.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by Devon Ravihansa on 9/21/2017.
 */
@Component
public class HandlerInterceptorAdapterImpl extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartManager cartManager;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        System.out.println("executed");
        Principal userPrincipal = request.getUserPrincipal();
        if(userPrincipal != null){
            User user = userRepository.findByUsername(userPrincipal.getName());
            Order order = orderService.getOrder(user);
            Cart cart = new Cart();
            for(OrderItem orderItem : order.getOrderItems()){
                cart.addItem(orderItem.getProduct(), orderItem.getQuantity());
            }
            mav.addObject("cart", cart);
        }else{
            mav.addObject("cart", cartManager.getCart(request.getSession()));
        }
    }
}
