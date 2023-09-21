package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TamanhoTests {
    private Tamanho tamanho = new Tamanho();

    @BeforeEach
    void setup() {
        tamanho.setId(1L);
        tamanho.setMaximoSabores(1);
        tamanho.setValor(10D);
        tamanho.setTamanho("Mini");
    }

    @Test
    void tamanhoIdTest(){
        assertEquals(1L, tamanho.getId());
    }
    @Test
    void tamanhoMaximoSaboresTest(){
        assertEquals(1, tamanho.getMaximoSabores());
    }
    @Test
    void tamanhoValorTest(){
        assertEquals(10D, tamanho.getValor());
    }
    @Test
    void tamanhoTest(){
        assertEquals("Mini", tamanho.getTamanho());
    }
    @Test
    void tamanhoAllArgsConstructorTest(){
        Tamanho tamanhoAllArgs = new Tamanho("Mini", 10D, 1);
        assertThat(tamanho).usingRecursiveComparison().ignoringFields("id").isEqualTo(tamanhoAllArgs);
    }

}
