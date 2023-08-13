package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

public class Enderecos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @NotNull @Column(name="id", unique = true)
    private Serial id;

    /*
    não criamos esse campo no DER,
    então criei com o tamanho 40 pois existem endereços bem grandes.
     */
    @Getter @Setter
    @Column(name="logradouro", length = 40)
    private String logradouro;

    @Getter @Setter
    @Column(name="numero")
    private Integer numero;

    @Getter @Setter
    @Column(name="complemento",length = 50)
    private String complemento;

    @Getter @Setter
    @NotNull @Column(name="bairro", nullable = false)
    private String bairro;

    @Getter @Setter
    @NotNull @Column(name="cep", nullable = false)
    private String cep;
}
