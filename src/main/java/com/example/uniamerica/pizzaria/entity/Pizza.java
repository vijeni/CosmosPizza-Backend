package com.example.uniamerica.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="pizzas", schema="public")
@AllArgsConstructor
@NoArgsConstructor
public class Pizza extends AbstractEntity {

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "sabores_pizza",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "sabor_id"))
    private List <Sabor> sabores;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(nullable = false)
    private Tamanho tamanho;

    @Getter @Setter
    @Column(name="observacao", length = 100)
    private String observacao;

    @Getter @Setter
    @ManyToMany(mappedBy = "pizzas")
    @JsonIgnore
    private List<Pedido> pedidos;
}
