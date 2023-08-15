package com.example.uniamerica.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoDTO {

    private Long id;


    private String logradouro;

    private Integer numero;


    private String complemento;


    private String bairro;


    private String cep;
}
