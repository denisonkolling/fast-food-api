package com.fastfood.dto;

import com.fastfood.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Integer id;

    private LocalDateTime data;

    private Long cpf;

    private List<ItemResponse> items;

    private Float totalValue;


    public OrderResponse(Integer id, LocalDateTime data, Long cpf, Float totalValue, List<OrderItem> orderItems) {
        this.id = id;
        this.data = data;
        this.cpf = cpf;
        this.totalValue = totalValue;
        this.items = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            var item = new ItemResponse(orderItem.getProductNumber(), orderItem.getQuantity(), orderItem.getValue());
            this.items.add(item);
        }
    }
}
