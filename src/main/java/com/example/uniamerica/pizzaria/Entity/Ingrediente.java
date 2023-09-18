package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name="ingredientes", schema = "public")
public class Ingrediente extends AbstractEntity {

    @NotNull @Getter @Setter
    @Column(name="nome", length = 20, nullable = false)
    private String nome;

    @NotNull @Getter @Setter
    @Column (name="quantidade", nullable = false)
    private Integer quantidade;

    @ManyToMany(mappedBy = "ingredientes")
    List<Sabor>sabores;
}
