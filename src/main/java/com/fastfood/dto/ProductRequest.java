package com.fastfood.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull
    private Long cpf;

    @NotEmpty
    private String name;

}
