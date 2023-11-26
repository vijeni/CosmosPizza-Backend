package com.example.uniamerica.pizzaria.dto;

import com.example.uniamerica.pizzaria.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioAuthDTO {
    private String clientId;
    private String username;
    private String password;
    private String grantType;
    private String role;

}
