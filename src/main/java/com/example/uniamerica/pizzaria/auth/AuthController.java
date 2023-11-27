package com.example.uniamerica.pizzaria.auth;

import com.example.uniamerica.pizzaria.dto.LoginDTO;
import com.example.uniamerica.pizzaria.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;
    @PostMapping
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO login) throws AccessDeniedException {
        return ResponseEntity.ok(service.login(login));
    }
}
