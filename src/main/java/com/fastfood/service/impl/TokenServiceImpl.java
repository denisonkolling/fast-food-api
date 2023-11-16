package com.fastfood.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fastfood.model.User;
import com.fastfood.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${secret}")

    private String secret;

    @Override
    public String findToken(String bearerToken) {
        final String bearer = "Bearer ";
        if (bearerToken == null || !bearerToken.startsWith(bearer))
            throw new JWTVerificationException("Invalid Authorization Header");
        String token = bearerToken.substring(bearer.length());
        return token;
    }

    @Override
    public String findSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    @Override
    public String createToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        Instant expiration = createExpiration(15);
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(expiration)
                .withIssuer("Lanchonete-API")
                .withClaim("roles", user.getRole().name())
                .sign(algorithm);
        return token;
    }

    private Instant createExpiration(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes)
                .atZone(ZoneId.systemDefault()).toInstant();
    }
}
