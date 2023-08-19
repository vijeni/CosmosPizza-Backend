package com.example.uniamerica.pizzaria.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class TamanhoDTO {

    private Long id;
    @NotNull(message = "O tamanho deve ser informado!")
    @Length(min = 1, max = 15, message = "O tamanho deve ter entre 1 e 15 caracteres")
    private String tamanho;
    @NotNull(message = "O valor deve ser informado!")
    private Double valor;
    @NotNull(message = "O máximo de sabores deve ser informado!")
    private int maximoSabores;
}
