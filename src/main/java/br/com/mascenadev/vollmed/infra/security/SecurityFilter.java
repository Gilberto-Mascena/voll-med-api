package br.com.mascenadev.vollmed.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recoverTokenJWT(request);
        System.out.println("Token JWT: " + tokenJWT);

        filterChain.doFilter(request, response);
    }

    private String recoverTokenJWT(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ") || authorizationHeader.endsWith(" ")) {
            throw new RuntimeException("Token JWT não enviado no cabeçalo authorization!");
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}