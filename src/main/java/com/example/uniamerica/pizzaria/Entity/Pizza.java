package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="pizzas", schema="public")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @NotNull
    @Column(name="id", unique = true)
    private Long id;

    @NotNull @Getter @Setter
    @OneToMany
    @JoinColumn(name="sabor_pizza", nullable = false)
    private List <Sabor> sabor;

    @NotNull @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho", nullable = false)
    private Tamanho tamanho;

    @Getter @Setter
    @Column(name="observacao", length = 100)
    private String observacao;
}
