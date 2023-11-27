package com.example.uniamerica.pizzaria.dto;

import com.example.uniamerica.pizzaria.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO{
    private String id;
    @NotNull(message = "Por favor, digite um username!")
    private String username;
    @NotNull(message="É obrigatório escolher o nível de acesso.")
    @Enumerated(EnumType.STRING)
    private Role role;
    private String cpf;
    private String token;
    private LocalDateTime cadastro;
    private LocalDateTime edicao;
    private LocalDateTime delecao;

    public void roleString(String role){
        this.role = Role.valueOf(role);
    }
    public String roleStringGet() {
        return this.role.toString();
    }

    public void desativar(){
        this.delecao = LocalDateTime.now();
    }
    public void ativar() {
        this.delecao = null;
    }
}
