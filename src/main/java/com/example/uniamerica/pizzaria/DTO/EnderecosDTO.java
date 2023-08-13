package com.example.uniamerica.pizzaria.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecosDTO {

    private Long id;


    private String logradouro;

    private Integer numero;


    private String complemento;


    private String bairro;


    private String cep;
}
