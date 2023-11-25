package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
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

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario{

    @Id
    @Getter @Setter
    @Column(name = "id", unique = true)
    private String id;

    @Getter
    @Setter
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Getter @Setter
    @Column(name="cpf", nullable = false)
    private String cpf;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Getter @Setter
    @Column(name = "data_Cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "data_edicao")
    private LocalDateTime edicao;

    @Getter @Setter
    @Column(name = "delecao")
    private LocalDateTime delecao;

    public void desativar(){
        this.delecao = LocalDateTime.now();
    }
    public void ativar() {
        this.delecao = null;
    }
    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();

    }
    @PreUpdate
    private void preUpdate(){
        this.edicao = LocalDateTime.now();
    }

}
