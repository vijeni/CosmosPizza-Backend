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
@Table(name="clientes", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends AbstractEntity {

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
    @JoinColumn(name = "enderecos_clientes")
    private Endereco endereco;

}
