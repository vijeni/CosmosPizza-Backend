package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="enderecos", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends AbstractEntity {

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

    @Getter  @Setter
    @Column(nullable = false)
    private String uf;

    @Getter  @Setter
    @Column(nullable = false)
    private String cidade;
}
