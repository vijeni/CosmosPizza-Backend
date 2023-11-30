package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class EnderecoTests {
    private Endereco endereco = new Endereco();

    @BeforeEach
    void setup() {
        endereco.setId(1L);
        endereco.setLogradouro("Rua A");
        endereco.setNumero(123);
        endereco.setComplemento("Complemento");
        endereco.setBairro("Bairro B");
        endereco.setCep("85851-010");
    }

    @Test
    void enderecoIdTest(){
        assertEquals(1L, endereco.getId());
    }
    @Test
    void enderecoLogradouroTest(){
        assertEquals("Rua A", endereco.getLogradouro());
    }
    @Test
    void enderecoNumeroTest(){
        assertEquals(123, endereco.getNumero());
    }
    @Test
    void enderecoComplementoTest(){
        assertEquals("Complemento", endereco.getComplemento());
    }
    @Test
    void enderecoBairroTest(){
        assertEquals("Bairro B", endereco.getBairro());
    }
    @Test
    void enderecoCepTest(){
        assertEquals("85851-010", endereco.getCep());
    }
//    @Test
//    void tamanhoAllArgsConstructorTest(){
//        Endereco enderecoAllArgs = new Endereco("Rua A", "123", "Complemento", "Bairro B", "85851-010");
//        assertThat(endereco).usingRecursiveComparison().ignoringFields("id").isEqualTo(enderecoAllArgs);
//    }
}
