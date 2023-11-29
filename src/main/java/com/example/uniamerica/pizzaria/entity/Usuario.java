package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @Column(name="cpf", unique = true)
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

    public void roleStringSet(String role){
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
    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();

    }
    @PreUpdate
    private void preUpdate(){
        this.edicao = LocalDateTime.now();
    }

}
