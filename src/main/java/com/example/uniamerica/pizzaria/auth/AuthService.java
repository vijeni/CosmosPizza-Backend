package com.example.uniamerica.pizzaria.auth;

import com.example.uniamerica.pizzaria.dto.LoginDTO;
import com.example.uniamerica.pizzaria.dto.UsuarioDTO;
import com.example.uniamerica.pizzaria.entity.Usuario;
import com.example.uniamerica.pizzaria.repository.UsuarioRepository;
import com.example.uniamerica.pizzaria.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Value("${security.jwt.token}")
    private String key;
    @Value("${security.jwt.auth-url}")
    private String authTokenUrl;

    private PublicKey decodeSecret(String secret) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao usara chave pública", e);
        }
    }
    public UsuarioDTO login(LoginDTO loginDTO){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        UsuarioDTO usuarioRetorno = new UsuarioDTO();
        Usuario usuarioBanco;
        String role = "NONE";
        String token;
        PublicKey publicKey = decodeSecret(key);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("client_id", "cosmos-pizza-api");
        formData.add("username", loginDTO.getUsername());
        formData.add("password", loginDTO.getPassword());
        formData.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);
        AuthResponse retorno = restTemplate.postForEntity(authTokenUrl,entity,AuthResponse.class).getBody();

        Assert.notNull(retorno, "Login inválido!");

        token = retorno.getAccess_token();
        Assert.notNull(token, "Login inválido!");

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(retorno.getAccess_token());

        Claims body = claimsJws.getBody();
        List<String> roles = (List<String>) body.get("realm_access", Map.class).get("roles");
        for (String r:
                roles) {
            if(r.equals("ADMIN") || r.equals("FUNCIONARIO")){
                role = r;
                break;
            }
        }
        String username = (String) body.get("preferred_username");
        String id = (String) body.get("sub");
        usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if(usuarioBanco != null){
            usuarioRetorno = usuarioService.toUsuarioDTO(usuarioBanco);
            if(!usuarioBanco.roleStringGet().equals(role)){
                System.out.println(role);
                usuarioBanco.roleString(role);
                usuarioRetorno = usuarioService.toUsuarioDTO(usuarioRepository.save(usuarioBanco));
            }
        }
        else{
            UsuarioDTO mock = new UsuarioDTO();
            mock.setId(id);
            mock.roleString(role);
            mock.setUsername(username);
            usuarioRetorno = usuarioService.post(mock);
        }
        usuarioRetorno.setToken(token);
        return usuarioRetorno;
    }
}
