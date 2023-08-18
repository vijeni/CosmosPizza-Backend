package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="sabor", schema = "public")
public class Sabor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter  @Column(name="id", unique = true)
    private Long id;

    @Getter @Setter
    @NotNull @Column(name = "nome",  nullable = false, length = 20, unique = true)
    private String nome;

    @Getter @Setter
    @Column(name="descricao", length = 50)
    private String descricao;

    @ManyToMany @Getter @Setter
    @JoinTable(
            name = "sabores_ingredientes",
            joinColumns = @JoinColumn(name = "sabor_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredientes_id"))
    private List <Ingrediente> ingredientes;

    @Getter @Setter
    @ManyToMany(mappedBy = "sabor")
    private List<Pizza> pizzas;

}
