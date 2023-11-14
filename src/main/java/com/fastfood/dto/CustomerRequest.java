package com.fastfood.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotNull(message = "Required field")
    private Long cpf;

    @NotEmpty(message = "Required field")
    private String name;
}
