package com.fastfood.dto;

import com.fastfood.model.Role;
import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String name;
    private String email;
    private Role role;

}
