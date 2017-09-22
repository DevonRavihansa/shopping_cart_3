package com.example.demo.controller;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Devon Ravihansa on 9/10/2017.
 */
@Controller
public class DefaultController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/shop")
    public String shop(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "shop";
    }

    @RequestMapping("/product/{sku}")
    public String product(@PathVariable("sku")String sku, Model model){
        Product product = productRepository.findBySku(sku);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping("cart")
    public String cart(){
        return "cart";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/test")
    public @ResponseBody Product test(){
        return productRepository.findByNameOrPrice("Milk Powder",295.50);
    }

    @RequestMapping("/order")
    public String order(){
        return "order";
    }

    @RequestMapping("/json")
    public String json(){
        return "json";
    }
}
