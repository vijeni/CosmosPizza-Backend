package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PedidoDTO {
    @NotNull(message = "Código do pedido não informado!")
    private Long codigoPedido;

    @NotNull(message = "Cliente não informado!")
    private Pessoa cliente;

    @NotNull(message = "Funcionário não informado!")
    private Pessoa funcionario;

    @NotNull(message = "Informe o status do pedido!")
    private Status status;

    @NotNull(message = "Valor do pedido não informado!")
    private Double valorPedido;
    private Double valorEntrega;
    @NotNull(message = "Valor total não informado!")
    private Double valorTotal;
    @NotNull(message = "Forma de pagamento não informado!")
    private Pagamento formaPagamento;
    private List<Produto> produtos;
    private  List <Pizza> pizzas;
}
