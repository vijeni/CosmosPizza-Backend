package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class PedidoDTO {
    private Long codigoPedido;
    @NotNull(message = "Cliente não informado!")
    private Pessoa cliente;
    @NotNull(message = "Funcionário não informado!")
    private Pessoa funcionario;
    private Status status;
    @NotNull(message = "Informe a necessidade de entrega")
    private boolean isEntrega;
    @NotNull(message = "Valor do pedido não informado!")
    private Double valorPedido;
    private LocalDateTime dataAbertura;
    @NotNull(message = "Informe a data de conclusão")
    private LocalDateTime dataConclusao;
    private Double valorEntrega;
    private Double valorTotal;
    @NotNull(message = "Forma de pagamento não informado!")
    private Pagamento formaPagamento;
    private List<ProdutoDTO> produtos;
    private List<Pizza> pizzas;


}
