package com.example.uniamerica.pizzaria.Entity;

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
    private String tamanho;

    @Getter @Setter
    @Column(nullable = false)
    private Double valor;

    @Getter @Setter
    @Column(nullable = false)
    private int maximoSabores;
}
