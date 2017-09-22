package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 9/14/2017.
 */
@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String statusCode;

    @OneToMany(mappedBy = "status")
    @JsonIgnoreProperties("status")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
