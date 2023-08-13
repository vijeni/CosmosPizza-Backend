package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.Enderecos;
import com.example.uniamerica.pizzaria.Entity.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PessoasDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private Enderecos enderecos;


    private TipoPessoa tipoPessoa;
}
