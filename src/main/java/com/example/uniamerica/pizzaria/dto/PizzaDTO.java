package com.example.uniamerica.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
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
public class PizzaDTO extends AbstractEntityDTO {

    @NotNull(message = "Informe o(s) sabor(es)")
    @NotEmpty(message = "Informe o(s) sabor(es)")
    private List <SaborDTO> sabores;

    @NotNull(message = "Informe o tamanho!")
    private TamanhoDTO tamanho;

    @Length(max = 100, message = "A observação deve ter no máximo 100 caracteres")
    private String observacao;
    @JsonIgnore
    private List<PedidoDTO> pedidos;

}
