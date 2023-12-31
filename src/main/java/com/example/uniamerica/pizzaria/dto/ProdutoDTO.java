package com.example.uniamerica.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO extends AbstractEntityDTO {

    @NotNull(message = "A descrição deve ser informada!")
    @NotBlank(message = "A descrição deve ser informada!")
    @Length(min = 3, max = 20, message = "A descrição deve ter no mínimo 3 e no máximo 20 caracteres")
    private String descricao;
    @NotNull(message = "A quantidade deve ser informada!")
    private Integer quantidadeEstoque;
    @Length(max = 50, message = "A observação deve ter no máximo 50 caracteres")
    private String observacao;
    @NotNull(message = "O valor unitário deve ser informado!")
    private Double valorUnitario;
    @JsonIgnore
    private List<PedidoDTO> pedidos;
}
