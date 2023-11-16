package com.fastfood.service.impl;

import com.fastfood.model.Customer;
import com.fastfood.model.Order;
import com.fastfood.model.OrderItem;
import com.fastfood.model.Product;
import com.fastfood.repository.OrderRepository;
import com.fastfood.service.CustomerService;
import com.fastfood.service.OrderService;
import com.fastfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Override
    public Order createOrder(Order order) {
        Customer customer = customerService.findCustomerById(order.getCustomer().getCpf());
        var newOrder = new Order(customer);
        for (OrderItem item : order.getItems()) {
            Product product = productService.findProductById(item.getProductNumber());
            newOrder.addProduct(product, item.getQuantity());
        }
        newOrder = orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found" + id));
    }

    @Override
    public void deleteOrder(Integer id) {
        boolean orderExists = orderRepository.existsById(id);
        if (!orderExists){
            throw new IllegalArgumentException("Order not found" + id);
        }
        orderRepository.deleteById(id);
    }
}
