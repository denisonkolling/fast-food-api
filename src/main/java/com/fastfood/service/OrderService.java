package com.fastfood.service;

import com.fastfood.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> findAllOrders();

    Order findOrderById(Integer id);

    String deleteOrder(Integer id);
}
