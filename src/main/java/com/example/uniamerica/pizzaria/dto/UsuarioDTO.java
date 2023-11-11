package com.example.uniamerica.pizzaria.dto;

import com.example.uniamerica.pizzaria.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO extends AbstractEntityDTO{
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private String cpf;

}
