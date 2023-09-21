package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.dto.EnderecoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class EnderecoDTOTests {
    private EnderecoDTO enderecoDTO = new EnderecoDTO();

    @BeforeEach
    void setup() {
        enderecoDTO.setId(1L);
        enderecoDTO.setLogradouro("Rua A");
        enderecoDTO.setNumero(123);
        enderecoDTO.setComplemento("Complemento");
        enderecoDTO.setBairro("Bairro B");
        enderecoDTO.setCep("85851-010");
    }

    @Test
    void enderecoIdTest(){
        assertEquals(1L, enderecoDTO.getId());
    }
    @Test
    void enderecoLogradouroTest(){
        assertEquals("Rua A", enderecoDTO.getLogradouro());
    }
    @Test
    void enderecoNumeroTest(){
        assertEquals(123, enderecoDTO.getNumero());
    }
    @Test
    void enderecoComplementoTest(){
        assertEquals("Complemento", enderecoDTO.getComplemento());
    }
    @Test
    void enderecoBairroTest(){
        assertEquals("Bairro B", enderecoDTO.getBairro());
    }
    @Test
    void enderecoCepTest(){
        assertEquals("85851-010", enderecoDTO.getCep());
    }
    @Test
    void tamanhoAllArgsConstructorTest(){
        EnderecoDTO enderecoAllArgs = new EnderecoDTO("Rua A", 123, "Complemento", "Bairro B", "85851-010");
        assertThat(enderecoDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(enderecoAllArgs);
    }
}
