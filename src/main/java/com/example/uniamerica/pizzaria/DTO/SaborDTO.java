package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Entity.Pizza;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SaborDTO {

    private Long id;

    private String nome;

    private String descricao;

    private List <Ingrediente> ingredientes;

    @JsonIgnore
    private  List<Pizza> pizzas;
}
