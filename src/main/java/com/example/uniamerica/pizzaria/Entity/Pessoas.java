package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pessoas_table", schema = "public")
public class Pessoas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @NotNull @Column(name="id", unique = true)
    private Long id;

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
    @JoinColumn(name = "enderecos_id")
    private Enderecos enderecos;
}
