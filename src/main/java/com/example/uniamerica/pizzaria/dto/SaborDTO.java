package com.example.uniamerica.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaborDTO extends AbstractEntityDTO {

    @NotNull(message = "Insira um nome!") @NotEmpty(message = "Insira um nome!")
    @Size(min = 3, max = 20, message = "No mínimo 3 e no máximo 20 caracteres!")
    private String nome;
    @Size(max = 50, message = "No máximo 50 caracteres!")
    private String descricao;

    private List <IngredienteDTO> ingredientes;

    @JsonIgnore
    private  List<PizzaDTO> pizzas;
}
