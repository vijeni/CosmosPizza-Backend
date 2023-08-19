package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tamanho", schema = "public")
public class Tamanho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name="id", unique = true)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String tamanho;

    @Getter @Setter
    @Column(nullable = false)
    private Double valor;

    @Getter @Setter
    @Column(nullable = false)
    private int maximoSabores;
}
