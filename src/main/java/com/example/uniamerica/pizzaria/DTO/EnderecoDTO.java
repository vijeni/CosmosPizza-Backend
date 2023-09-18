package com.example.uniamerica.pizzaria.DTO;

import com.example.uniamerica.pizzaria.Entity.AbstractEntityDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class EnderecoDTO extends AbstractEntityDTO {

    @Length(max = 40, message = "No máximo 40 caracteres!")
    private String logradouro;

    private Integer numero;

    @Length(max = 50, message = "No máximo 50 caracteres!")
    private String complemento;

    @NotNull(message = "Insira um bairro!")
    @NotEmpty(message = "Insira um bairro!")
    private String bairro;

    @NotNull(message = "Insira um CEP!")
    @NotEmpty(message = "Insira um CEP!")
    private String cep;
}
