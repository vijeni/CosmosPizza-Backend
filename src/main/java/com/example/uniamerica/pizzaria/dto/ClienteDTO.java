package com.example.uniamerica.pizzaria.dto;

import com.example.uniamerica.pizzaria.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends AbstractEntityDTO {

    @NotNull(message = "Insira um nome!") @NotEmpty(message = "Insira um nome!")
    @Size(min = 3, max = 40,message = "No mínimo 3 e no máximo 40 caracteres!")
    private String nome;
    @NotNull(message = "Insira um CPF!") @NotEmpty(message = "Insira um CPF!")
    @CPF(message = "Este CPF não é válido." )
    private String cpf;

    @Size( max = 20,message = "No máximo 20 caracteres!")
    private String telefone;
    @NotNull(message = "Insira um endereço!")
    private EnderecoDTO endereco;

    @NotNull(message = "Defina o tipo de pessoa a ser cadastrada.")
    private Role role;
}
