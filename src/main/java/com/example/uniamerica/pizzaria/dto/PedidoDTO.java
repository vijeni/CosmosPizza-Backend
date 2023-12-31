package com.example.uniamerica.pizzaria.dto;

import com.example.uniamerica.pizzaria.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO extends AbstractEntityDTO {
    @NotNull(message = "Cliente não informado!")
    private ClienteDTO cliente;
    @NotNull(message = "Funcionário não informado!")
    private UsuarioDTO funcionario;
    private Status status;
    @NotNull(message = "Informe a necessidade de entrega")
    private boolean entrega;
    private Double valorPedido = (double) 0;
//    @NotNull(message = "Informe a data de abertura")
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;
    private Double valorEntrega;
    private Double valorTotal = (double) 0;
    @NotNull(message = "Forma de pagamento não informado!")
    private Pagamento formaPagamento;
    private List<ProdutoDTO> produtos;
    private List<PizzaDTO> pizzas;
    public void addValorPedido(Double valor){
        this.valorPedido += valor;
    }
    public void addValorTotal(Double valor){
        this.valorTotal += valor;
    }
}
