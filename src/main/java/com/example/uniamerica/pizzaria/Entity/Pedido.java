package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table (name="pedidos", schema = "public")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="codigo_pedido", unique = true)
    private Long codigoPedido;

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    @Getter @Setter
    private Pessoa cliente;

    @ManyToOne
    @JoinColumn(name = "funcionarios_id")
    @Getter @Setter
    private Pessoa funcionario;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="status_pedido", nullable = false)
    private Status status;

    @Getter @Setter
    @Column(name="valor_pedido",nullable = false)
    private Double valorPedido;

    @Column(name="valor_entrega")
    @Getter @Setter
    private Double valorEntrega;

    @Getter @Setter @Column(name="valor_total", nullable = false)
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name="forma_pagamento", nullable = false)
    private Pagamento formaPagamento;

    @Getter @Setter
    @Column(name = "is_entrega", nullable = false)
    private boolean isEntrega;

    @Getter @Setter
    @Column(name = "data_abertura", nullable = false)
    private Date dataAbertura;

    @Getter @Setter
    @Column(name = "data_conclusao", nullable = false)
    private Date dataConclusao;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "pedidos_pizzas",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "pizzas_id"))
    private  List <Pizza> pizzas;
}
