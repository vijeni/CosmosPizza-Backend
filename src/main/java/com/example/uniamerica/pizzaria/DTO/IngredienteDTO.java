package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.AbstractEntityDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IngredienteDTO extends AbstractEntityDTO {

    @NotNull(message = "Insira um nome!") @NotEmpty(message = "Insira um nome!")
    @Size(min = 2, max = 20, message = "No mínimo 2 e no máximo 20 caracteres para o nome!")
    private String nome;

    @NotNull(message = "Insira uma quantidade!")
    private Integer quantidade;

}
