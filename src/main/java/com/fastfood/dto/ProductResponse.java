package com.fastfood.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Integer productNumber;

    private String category;

    private String name;

    private Float price;

}
