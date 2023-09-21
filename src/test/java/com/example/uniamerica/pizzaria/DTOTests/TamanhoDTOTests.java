package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class TamanhoDTOTests {
    private TamanhoDTO tamanhoDTO = new TamanhoDTO();

    @BeforeEach
    void setup() {
        tamanhoDTO.setId(1L);
        tamanhoDTO.setMaximoSabores(1);
        tamanhoDTO.setValor(10D);
        tamanhoDTO.setTamanho("Mini");
    }

    @Test
    void tamanhoIdTest(){
        assertEquals(1L, tamanhoDTO.getId());
    }
    @Test
    void tamanhoMaximoSaboresTest(){
        assertEquals(1, tamanhoDTO.getMaximoSabores());
    }
    @Test
    void tamanhoValorTest(){
        assertEquals(10D, tamanhoDTO.getValor());
    }
    @Test
    void tamanhoTest(){
        assertEquals("Mini", tamanhoDTO.getTamanho());
    }
    @Test
    void tamanhoAllArgsConstructorTest(){
        TamanhoDTO tamanhoAllArgs = new TamanhoDTO("Mini", 10D, 1);
        assertThat(tamanhoDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(tamanhoAllArgs);
    }

}
