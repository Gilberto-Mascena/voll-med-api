package br.com.mascenadev.vollmed.service;

import br.com.mascenadev.vollmed.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") // @value is used to inject values from application.properties.
    private String secret;

    public String generateToken(User user) {

        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    public String getSubject(String tokenJWT) {

        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!", exception);
        }
    }

    private Instant expirationDate() {

        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
    }
}