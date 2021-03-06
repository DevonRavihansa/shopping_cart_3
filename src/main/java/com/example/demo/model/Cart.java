package com.example.demo.model;

import com.example.demo.entity.Product;
import com.example.demo.service.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devon Ravihansa on 9/10/2017.
 */
public class Cart {
    final List<CartItem> items;
    int itemCount;
    double total;

    public Cart() {
        items = new ArrayList<>();
        itemCount = 0;
        total = 0.0;
    }

    public CartItem getItem(Product product){
        for(CartItem item : items){
            if(item.getProduct().getId() == product.getId()){
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getItemCount() {
        itemCount = 0;
        for(CartItem item : items){
            itemCount += item.getQuantity();
        }
        return itemCount;
    }

    public void addItem(Product product, int quantity){
        CartItem item = getItem(product);

        if(item != null){
            item.setQuantity(item.getQuantity() + quantity);
        }else{
            item = new CartItem(product);
            item.setQuantity(quantity);
            items.add(item);
        }
    }

    public void updateItem(Product product, int quantity){
        CartItem item = getItem(product);

        if(item != null){
            item.setQuantity(quantity);
        }else{
            // throw new ProductNotFoundException();
        }
    }

    public void removeItem(Product product){
        CartItem item = getItem(product);

        if(item != null){
            items.remove(item);
        }else{
            // throw new ProductNotFoundException();
        }
    }

    public void clear(){
        items.clear();
        itemCount = 0;
        total = 0.0;
    }

    public double getTotal() {
        total = 0.0;
        for(CartItem item : items){
            total += item.getSubTotal();
        }
        return total;
    }
}
