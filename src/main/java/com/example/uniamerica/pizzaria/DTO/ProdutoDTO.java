package com.example.uniamerica.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private Integer quantidade;

    private String descricao;
}
