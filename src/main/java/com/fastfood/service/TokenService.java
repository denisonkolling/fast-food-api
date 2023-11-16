package com.fastfood.service;

import com.fastfood.model.User;

public interface TokenService {

    String findToken(String bearerToken);

    String findSubject(String token);

    String createToken(User user);

}
