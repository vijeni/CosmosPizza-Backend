package com.example.uniamerica.pizzaria.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IngredientesDTO {

    private long id;

    private String nome;


    private Integer quantidade;

}
