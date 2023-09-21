package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name="ingredientes", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente extends AbstractEntity {

    @NotNull @Getter @Setter
    @Column(name="nome", length = 20, nullable = false)
    private String nome;

    @NotNull @Getter @Setter
    @Column (name="quantidade", nullable = false)
    private Integer quantidade;

    @Getter @Setter
    @ManyToMany(mappedBy = "ingredientes")
    List<Sabor>sabores;
}
