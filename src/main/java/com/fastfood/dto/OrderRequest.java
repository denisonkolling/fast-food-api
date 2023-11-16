package com.fastfood.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    @NotNull
    private Long cpf;

    @NotNull
    private List<ItemRequest> items;




}
