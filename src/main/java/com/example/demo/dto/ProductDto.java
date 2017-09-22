package com.example.demo.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Devon Ravihansa on 9/21/2017.
 */
public class ProductDto {

    private String sku;

    @NotNull(message = "name can't be empty!")
    private String name;

    private double price;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
