package com.fastfood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Integer productNumber;

    private Integer quantity;

    private Float ItemValue;

}

