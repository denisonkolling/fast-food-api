package com.fastfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CPF_CUSTOMER", referencedColumnName = "CPF")
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderItem> items;


    @Transient
    private Float totalValue;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(Customer customer) {
        this();
        this.customer = customer;
    }


}
