package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="produtos", schema = "public")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @NotNull
    @Column(name="id", unique = true)
    private Long id;

    @Getter @Setter @NotNull
    @Column(name="nome", nullable = false, length = 20)
    private String nome;

    /*
    No DER não foi definido como NotNull, mas como estamos cadastrando um novo produto,
    acho que deveríamos cadastrar a quantidade também, para o controle de estoque.
     */
    @Getter @Setter @NotNull
    @Column(name="quantidade", nullable = false)
    private Integer quantidade;

    @Getter @Setter
    @Column(name = "descricao", length = 50)
    private String descricao;
}
