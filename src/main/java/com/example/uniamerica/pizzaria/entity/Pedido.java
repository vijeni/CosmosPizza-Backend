package com.example.uniamerica.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name="pedidos", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends  AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    @Getter @Setter
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionarios_id")
    @Getter @Setter
    private Usuario funcionario;

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
    @Column(name = "entrega", nullable = false)
    private boolean entrega;

    @Getter @Setter
    @Column(name = "data_abertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Getter @Setter
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @Getter @Setter
    @Cascade(CascadeType.ALL)
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "pedidos_pizzas",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "pizzas_id"))
    private  List <Pizza> pizzas;
}
