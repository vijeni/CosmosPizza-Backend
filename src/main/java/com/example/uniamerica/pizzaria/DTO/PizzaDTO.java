package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Pedido;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter @Setter
public class PizzaDTO {

    private Long id;

    @NotNull(message = "Informe o(s) sabor(es)")
    @NotEmpty(message = "Informe o(s) sabor(es)")
    private List <SaborDTO> sabor;

    @NotNull(message = "Informe o tamanho!")
    private Tamanho tamanho;

    @Length(max = 100, message = "A observação deve ter no máximo 100 caracteres")
    private String observacao;
    private List<PedidoDTO> pedidos;

}
