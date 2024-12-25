package com.marcosbrito.compass.spring.security.demo.web.token.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.marcosbrito.compass.spring.security.demo.entites.Pessoa;

@Service
public class TokenService {

    public static final String SECRET_KEY = "0123456789-0123456789-0123456789"; // Essa chave aqui é usada pra mudar a
                                                                                // forma com que meu algoritmo é
    // criado, cada chave faz com que as repostas mudem.

    private String generateUser(Pessoa user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create().withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpireTime())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro com time", e);
        }

    }

    private Instant getExpireTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private String validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException ex) {
            throw new RuntimeException(String.format("Token invalido %s", ex.getMessage()));
        }
    }

}
