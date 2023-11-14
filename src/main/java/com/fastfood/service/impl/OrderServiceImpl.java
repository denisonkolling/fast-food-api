package com.fastfood.service.impl;

import com.fastfood.model.Order;
import com.fastfood.repository.OrderRepository;
import com.fastfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public List<Order> findAllOrders() {
        return null;
    }

    @Override
    public Order findOrderById(Integer id) {
        return null;
    }

    @Override
    public String deleteOrder(Integer id) {
        return null;
    }
}
