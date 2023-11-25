package com.example.uniamerica.pizzaria.auth;

import com.example.uniamerica.pizzaria.dto.UsuarioAuthDTO;
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
    @PostMapping
    public ResponseEntity<String> geraToken(@RequestBody UsuarioAuthDTO usuarioAuth){
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", usuarioAuth.getClientId());
        formData.add("username", usuarioAuth.getUsername());
        formData.add("password", usuarioAuth.getPassword());
        formData.add("grant_type", usuarioAuth.getGrantType());

        HttpEntity<MultiValueMap<String, String>> entity = new  HttpEntity<MultiValueMap<String, String>>(formData, headers);

        return rt.postForEntity("http://localhost:8080/auth/realms/cosmos-pizza/protocol/openid-connect/token",entity, String.class);
    }
}
