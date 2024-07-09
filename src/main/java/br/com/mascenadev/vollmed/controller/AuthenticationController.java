package br.com.mascenadev.vollmed.controller;

import br.com.mascenadev.vollmed.dto.LoginDTO;
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

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDTO data) {

        var token = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var authentication = authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
