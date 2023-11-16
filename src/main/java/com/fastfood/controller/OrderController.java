package com.fastfood.controller;

import com.fastfood.dto.ItemRequest;
import com.fastfood.dto.OrderRequest;
import com.fastfood.model.Customer;
import com.fastfood.model.Order;
import com.fastfood.dto.OrderResponse;
import com.fastfood.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        List<Order> orders = orderService.findAllOrders();
        var response = orders
                .stream()
                .map(order -> new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.getCustomer().getCpf(),
                order.getTotalValue(),
                order.getItems()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        var order = new Order();
        order.setCustomer(new Customer(request.getCpf()));
        for (ItemRequest itemRequest : request.getItems()) {
            order.addProduct(itemRequest.getProductNumber(), itemRequest.getQuantity());
        }
        order = orderService.createOrder(order);
        var response = new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.getCustomer().getCpf(),
                order.getTotalValue(),
                order.getItems());
        return ResponseEntity.created(URI.create(order.getId().toString())).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
