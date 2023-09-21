package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tamanho", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Tamanho extends AbstractEntity {

    @Getter @Setter
    @Column(nullable = false)
    private String nome;

    @Getter @Setter
    @Column(nullable = false)
    private Double valor;

    @Getter @Setter
    @Column(nullable = false)
    private int maximoSabores;
}
