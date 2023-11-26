package com.example.uniamerica.pizzaria.auth;

import com.example.uniamerica.pizzaria.dto.UsuarioAuthDTO;
import com.example.uniamerica.pizzaria.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;
    @PostMapping
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioAuthDTO usuarioAuth){
        return ResponseEntity.ok(service.login(usuarioAuth));
    }
}
