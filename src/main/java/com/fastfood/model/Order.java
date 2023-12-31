package com.fastfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void addProduct(Product product, int qty) {
        if (items == null)
            items = new ArrayList<>();
        float value = product.getPrice() * qty;
        var orderItem = new OrderItem(this, product.getNumber(), qty, value);
        items.add(orderItem);
    }

    public void addProduct(Integer productNumber, int qty) {
        if (items == null)
            items = new ArrayList<>();
        var orderItem = new OrderItem(this, productNumber, qty, 0F);
        items.add(orderItem);
    }

    public Float getTotalValue() {
        if (items == null) return 0F;
        Float total = items.stream().map(OrderItem::getValue).reduce(0F, Float::sum);
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
