package com.fastfood.dto;

import com.fastfood.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull(message = "Required field")
    private String name;

    @NotNull(message = "Required field")
    private String email;

    @NotNull(message = "Required field")
    private Role role;


}
