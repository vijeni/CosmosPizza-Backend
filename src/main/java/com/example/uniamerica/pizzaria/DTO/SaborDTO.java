package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Ingredientes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SaborDTO {

    private Long id;

    private String nome;

    private String descricao;

    private List <Ingredientes> ingredientes;
}
