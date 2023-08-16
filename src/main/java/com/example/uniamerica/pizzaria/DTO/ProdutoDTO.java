package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Pedido;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter @Setter
public class ProdutoDTO {
    private Long id;
    @NotNull(message = "O nome deve ser informado!")
    @Length(min = 3, max = 20, message = "O nome deve ter no mínimo 3 e no máximo 20 caracteres")
    private String nome;
    @NotNull(message = "A quantidade deve ser informada!")
    private Integer quantidade;
    @Length(max = 50, message = "A descrição deve ter no máximo 50 caracteres")
    private String descricao;
    private List<PedidoDTO> pedidos;
}
