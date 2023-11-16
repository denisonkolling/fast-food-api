package com.fastfood.service.impl;

import com.fastfood.model.User;
import com.fastfood.service.AuthenticationService;
import com.fastfood.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String authentication(String email, String password) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(authToken);
            var user  = (User) authenticate.getPrincipal();
            String token = tokenService.createToken(user);
            return token;
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid User or Password");
        }
    }
}
