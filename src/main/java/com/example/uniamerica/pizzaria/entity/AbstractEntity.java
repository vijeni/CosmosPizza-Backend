package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "data_Cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "data_edicao")
    private LocalDateTime edicao;

    @Getter @Setter
    @Column(name = "delecao")
    private LocalDateTime delecao;

    @Getter @Setter
    @Column(name="isAtivo")
    private Boolean isAtivo;

@PrePersist
    private void prePersist(){
    this.cadastro = LocalDateTime.now();
    this.isAtivo = true;
}

@PreUpdate
    private void preUpdate(){
    this.edicao = LocalDateTime.now();
}



}
