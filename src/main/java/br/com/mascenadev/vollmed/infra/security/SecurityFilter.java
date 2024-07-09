package br.com.mascenadev.vollmed.infra.security;

import br.com.mascenadev.vollmed.service.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       var tokenJWT = recoverTokenJWT(request);
        System.out.println("Token JWT: " + tokenJWT);

        var subject = tokenService.getSubject(tokenJWT);
        System.out.println("Usuário autenticado: " + subject);

        filterChain.doFilter(request, response);
    }

    private String recoverTokenJWT(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho authorization!");
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}