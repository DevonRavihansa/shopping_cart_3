package com.example.demo.service;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderService;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.Cart;
import com.example.demo.model.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Devon Ravihansa on 9/13/2017.
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private CartManager cartManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {

        User user = userRepository.findByUsername(auth.getName());
        Cart cart = cartManager.getCart(request.getSession());
        orderService.appendCart(user, cart);

        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(savedRequest != null){
            response.sendRedirect(savedRequest.getRedirectUrl());
        }else{
            response.sendRedirect("/");
        }
    }
}
