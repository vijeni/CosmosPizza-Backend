package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="pedidos", schema = "public")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @NotNull
    @Column(name="id", unique = true)
    private Long id;

    /*
    Utilizei ManyToOne aqui, pois OneToMany deveria ser uma Lista.
     */
    @ManyToOne
    @Column(name = "pessoas_id")
    @Getter @Setter
    private Pessoas pessoas;

    @Getter @Setter
    @NotNull @Column(name="codigo_pedido", nullable = false, unique = true)
    private String codigoPedido;

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
    private Double valorEntrega;

    @NotNull
    @Getter @Setter @Column(name="valor_total", nullable = false)
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    @NotNull @Column(name="forma_pagamento", nullable = false)
    private Pagamento formaPagamento;
}
