package com.fastfood.service;

import com.fastfood.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> findAllUsers();

}
