package com.fastfood.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CustomerRequest {

    @NotNull(message = "Campo obrigatório")
    private Long cpf;

    @NotEmpty(message = "Campo obrigatório")
    private String nome;
}
