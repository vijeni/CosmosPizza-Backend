package com.example.uniamerica.pizzaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="pizzas", schema="public")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id", unique = true)
    private Long id;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "sabores_pizza",
            joinColumns = @JoinColumn(name = "sabor_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List <Sabor> sabores;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho", nullable = false)
    private Tamanho tamanho;

    @Getter @Setter
    @Column(name="observacao", length = 100)
    private String observacao;

    @Getter @Setter
    @ManyToMany(mappedBy = "pizzas")
    @JsonIgnore
    private List<Pedido> pedidos;
}
