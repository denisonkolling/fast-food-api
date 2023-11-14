package com.fastfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS_ITEMS")
@IdClass(OrderItemId.class)
public class OrderItem {

@Id
@ManyToOne
@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
private Order order;

@Id
@Column(name = "PRODUCT_NUM", nullable = false)
private Integer productNumber;

private int quantity;

@Column(name = "ITEM_VL")
private float value;

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order +
                ", productNumber=" + productNumber +
                ", quantity=" + quantity +
                ", value=" + value +
                '}';
    }
}
