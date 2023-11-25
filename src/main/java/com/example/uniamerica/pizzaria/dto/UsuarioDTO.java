package com.example.uniamerica.pizzaria.dto;

import com.example.uniamerica.pizzaria.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO{
    private String id;
    @NotNull(message = "Por favor, digite um username!")
    private String username;

    @NotNull(message = "Por favor, escolha uma senha!")
    private String password;

    @NotNull(message="É obrigatório escolher o nível de acesso.")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "É obrigatório preencher o CPF.")
    private String cpf;

    private LocalDateTime cadastro;
    private LocalDateTime edicao;
    private LocalDateTime delecao;

    public void desativar(){
        this.delecao = LocalDateTime.now();
    }
    public void ativar() {
        this.delecao = null;
    }
}
