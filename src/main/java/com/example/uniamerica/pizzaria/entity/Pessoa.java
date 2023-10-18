package com.example.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="pessoas_table", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa extends AbstractEntity {

    @Getter @Setter
    @NotNull @Size(min = 2, max = 40)
    @Column(name="nome", length = 40, nullable = false)
    private String nome;

    @Getter @Setter
    @NotNull @Column(name="cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Getter @Setter
    @Column(name="telefone", length = 20)
    private String telefone;

    @Getter @Setter
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "enderecos_pessoas")
    private Endereco endereco;

    @NotNull
    @Getter @Setter
    @Column(name="tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;
}
