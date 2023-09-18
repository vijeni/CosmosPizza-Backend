package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {

private Long id;
private LocalDateTime cadastro;
private LocalDateTime edicao;
private LocalDateTime delecao;

@PrePersist
    private void prePersist(){
    this.cadastro = LocalDateTime.now();
}

@PreUpdate
    private void preUpdate(){
    this.edicao = LocalDateTime.now();
}



}
