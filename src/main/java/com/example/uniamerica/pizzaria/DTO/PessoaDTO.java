package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Endereco;
import com.example.uniamerica.pizzaria.Entity.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PessoaDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private Endereco endereco;


    private TipoPessoa tipoPessoa;
}
