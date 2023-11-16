package com.fastfood.controller;

import com.fastfood.dto.UserRequest;
import com.fastfood.dto.UserResponse;
import com.fastfood.model.User;
import com.fastfood.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        var response = users.stream().map(user -> mapper.map(user, UserResponse.class)).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> inserir(@RequestBody @Valid UserRequest request) {
        User user = mapper.map(request, User.class);
        user = userService.createUser(user);
        var response = mapper.map(user, UserResponse.class);
        return ResponseEntity.created(URI.create(user.getId().toString())).body(response);
    }

}
