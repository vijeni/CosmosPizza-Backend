package com.example.uniamerica.pizzaria.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteDTO extends AbstractEntityDTO {

    @NotNull(message = "Insira um nome!") @NotEmpty(message = "Insira um nome!")
    @Size(min = 2, max = 20, message = "No mínimo 2 e no máximo 20 caracteres para o nome!")
    private String nome;

    @NotNull(message = "Insira uma quantidade!")
    private Integer quantidade;

}
