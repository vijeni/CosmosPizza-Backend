package com.example.uniamerica.pizzaria.auth;

import com.example.uniamerica.pizzaria.dto.UsuarioAuthDTO;
import com.example.uniamerica.pizzaria.dto.UsuarioDTO;
import com.example.uniamerica.pizzaria.entity.Usuario;
import com.example.uniamerica.pizzaria.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
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
    String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvraD0asmz6vDExl4XpYdmKgJJp5x5Erc3Fw9AMtKAqbZ9nJl2Kp/IzxP/DNHcRtwIwb51xvHuoLtJVgGworLXoG8P7skjqBpn/5ONubVaadW2Rw0NyNEGRLQwlKp40d6C2dcOyIkhP+ulh01HxWEdum4s8HSmWXoYPoroGd5CHXhQJs/rlhNh3hDZdLuzrBaD84ekTCm3rG8wqmQY5k1gjGUD+wYc0z8yYDja6i/hsD++TnGl7Cz0TKcWo8mTRVjbFEGLiGW88mZhd+PE6nkpdaoeRLunGxU/Lg04yERroLzrPkqf3sgMo+a2tTXyLvojLbFaCuKzmVAlcuAuPDtOQIDAQAB";
    PublicKey publicKey = getRSAPublicKey(key);

    @Autowired
    UsuarioService usuarioService;
    private PublicKey getRSAPublicKey(String chaveRSAPublica) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(chaveRSAPublica);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter a chave pública RSA", e);
        }
    }
    public UsuarioDTO login(UsuarioAuthDTO usuarioAuth){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", usuarioAuth.getClientId());
        formData.add("username", usuarioAuth.getUsername());
        formData.add("password", usuarioAuth.getPassword());
        formData.add("grant_type", usuarioAuth.getGrantType());
        HttpEntity<MultiValueMap<String, String>> entity = new  HttpEntity<MultiValueMap<String, String>>(formData, headers);
        AuthDTO retorno = rt.postForEntity("http://localhost:8080/auth/realms/cosmos-pizza/protocol/openid-connect/token",entity, AuthDTO.class).getBody();

        String token = retorno.getAccess_token();
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(retorno.getAccess_token());

        Claims body = claimsJws.getBody();
        List<String> roles = (List<String>) body.get("realm_access", Map.class).get("roles");
        String username = (String) body.get("preferred_username");
        String id = (String) body.get("sub");

        Usuario usuarioBanco = usuarioService.usuarioUsername(usuarioAuth.getUsername());
        UsuarioDTO usuarioRetorno;
        if(usuarioBanco != null){
            usuarioRetorno = usuarioService.toUsuarioDTO(usuarioBanco);
            usuarioRetorno.setToken(token);
        }
        else{
            UsuarioAuthDTO usuarioAuthDTO = new UsuarioAuthDTO();
            usuarioAuthDTO.setClientId(id);
            usuarioAuthDTO.setUsername(username);
            for (String role:
                 roles) {
                if(role.equals("ADMIN") || role.equals("FUNCIONARIO")){
                    usuarioAuthDTO.setRole(role);
                    break;
                }
            }

            usuarioRetorno =  usuarioService.post(usuarioService.authToUsuarioDTO(usuarioAuthDTO));
            usuarioRetorno.setToken(token);
        }
        return usuarioRetorno;
    }
}
