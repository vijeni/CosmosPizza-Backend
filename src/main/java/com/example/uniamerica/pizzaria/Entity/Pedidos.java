package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name="pedidos", schema = "public")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @NotNull
    @Column(name="codigo_pedido", unique = true)
    private Long codigoPedido;

    /*
    Utilizei ManyToOne aqui, pois OneToMany deveria ser uma Lista.
     */
    @ManyToOne
    @Column(name = "pessoas_id")
    @Getter @Setter
    private Pessoas pessoas;

    @Getter @Setter @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="status_pedido", nullable = false)
    private Status status;

    @NotNull @Column(name="valor_pedido",nullable = false)
    @Getter @Setter
    private Double valorPedido;

    /*
    no DER o valor da entrega é NotNull,
    mas deixei sem pois pode ser uma retirada em balcão, por exemplo.
     */
    @Column(name="valor_entrega")
    @Getter @Setter
    private Double valorEntrega;

    @NotNull
    @Getter @Setter @Column(name="valor_total", nullable = false)
    private Double valorTotal;

    @Enumerated(EnumType.STRING) @Getter @Setter
    @NotNull @Column(name="forma_pagamento", nullable = false)
    private Pagamento formaPagamento;

    /*
    Criei uma lista aqui, para usarmos OneToMany
     */
    @OneToMany @Getter @Setter
    @JoinColumn(name="pedidos_produtos")
    private List<Produtos> produtos;

    @ManyToMany @Getter @Setter
    @JoinColumn(name="pedidos_pizza")
    private  List <Pizzas> pizza;
}
