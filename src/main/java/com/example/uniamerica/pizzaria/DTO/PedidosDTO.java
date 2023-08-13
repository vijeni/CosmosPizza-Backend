package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PedidosDTO {

    private Long codigoPedido;

    /*
    Utilizei ManyToOne aqui, pois OneToMany deveria ser uma Lista.
     */

    private Pessoas pessoas;


    private Status status;


    private Double valorPedido;


    private Double valorEntrega;


    private Double valorTotal;


    private Pagamento formaPagamento;


    private List<Produtos> produtos;


    private  List <Pizzas> pizza;
}
