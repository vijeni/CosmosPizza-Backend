package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PedidoDTO {

    private Long codigoPedido;

    /*
    Utilizei ManyToOne aqui, pois OneToMany deveria ser uma Lista.
     */

    private Pessoa pessoa;


    private Status status;


    private Double valorPedido;


    private Double valorEntrega;


    private Double valorTotal;


    private Pagamento formaPagamento;


    private List<Produto> produtos;


    private  List <Pizza> pizza;
}
