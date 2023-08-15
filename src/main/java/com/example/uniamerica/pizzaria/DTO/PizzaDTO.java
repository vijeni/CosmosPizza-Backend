package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PizzaDTO {

    private Long id;


    private List <Sabor> sabor;

    private Tamanho tamanho;

    private String observacao;
}
