package com.example.uniamerica.pizzaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="sabor", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Sabor extends AbstractEntity{

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
    @JsonIgnore
    @ManyToMany(mappedBy = "sabores")
    private List<Pizza> pizzas;
}
