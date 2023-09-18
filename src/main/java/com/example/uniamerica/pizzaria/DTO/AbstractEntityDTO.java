package com.example.uniamerica.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public class AbstractEntityDTO {

    private Long id;

    private LocalDateTime cadastro;

    private LocalDateTime edicao;

    private boolean ativo;

    private LocalDateTime delecao;

}
