package com.example.demo.controller;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.model.Cart;
import com.example.demo.model.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 9/11/2017.
 */
@RestController
@RequestMapping("/rest/cart")
public class RestCartController {

    @Autowired
    private CartManager cartManager;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Cart getCart(HttpSession session){
        return cartManager.getCart(session);
    }

    @PostMapping
    public Cart addItem(HttpSession session, @RequestParam("sku") String sku,
                        @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity){
        Product product = productRepository.findBySku(sku);
        Cart cart = cartManager.getCart(session);
        cart.addItem(product, quantity);
        return cart;
    }

    @PutMapping
    public Cart updateItem(HttpSession session, @RequestParam("sku") String sku, @RequestParam("quantity") int quantity){
        Product product = productRepository.findBySku(sku);
        Cart cart = cartManager.getCart(session);
        cart.updateItem(product, quantity);
        return cart;
    }

    @DeleteMapping
    public Cart removeItem(HttpSession session, @RequestParam("sku") String sku){
        Product product = productRepository.findBySku(sku);
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
        return cart;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id")Product product){
        return new ResponseEntity(product, HttpStatus.OK);
    }
}
