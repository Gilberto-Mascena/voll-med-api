package br.com.mascenadev.vollmed.infra.security;

import br.com.mascenadev.vollmed.repository.UserRepository;
import br.com.mascenadev.vollmed.service.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Resource
    private TokenService tokenService;

    @Resource
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recoverTokenJWT(request);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var user = userRepository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverTokenJWT(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}