package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.AbstractEntityDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter @Setter
public class ProdutoDTO extends AbstractEntityDTO {

    @NotNull(message = "O nome deve ser informado!")
    @Length(min = 3, max = 20, message = "O nome deve ter no mínimo 3 e no máximo 20 caracteres")
    private String nome;
    @NotNull(message = "A quantidade deve ser informada!")
    private Integer quantidadeEstoque;
    @Length(max = 50, message = "A descrição deve ter no máximo 50 caracteres")
    private String descricao;
    @NotNull(message = "O valor unitário deve ser informado!")
    private Double valorUnitario;
    @JsonIgnore
    private List<PedidoDTO> pedidos;
}
