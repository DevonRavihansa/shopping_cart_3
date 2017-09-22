package com.example.demo.model;

import com.example.demo.entity.Product;

/**
 * Created by Devon Ravihansa on 9/10/2017.
 */
public class CartItem {

    final Product product;
    private int quantity;
    double subTotal;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.subTotal = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal = product.getPrice() * quantity;
    }
}
