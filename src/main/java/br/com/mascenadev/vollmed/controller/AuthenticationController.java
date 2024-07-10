package br.com.mascenadev.vollmed.controller;

import br.com.mascenadev.vollmed.dto.token.DataTokenDTO;
import br.com.mascenadev.vollmed.dto.login.LoginDTO;
import br.com.mascenadev.vollmed.entities.User;
import br.com.mascenadev.vollmed.service.TokenService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDTO data) {

        var AuthenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var authentication = authenticationManager.authenticate(AuthenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenDTO(tokenJWT));
    }
}