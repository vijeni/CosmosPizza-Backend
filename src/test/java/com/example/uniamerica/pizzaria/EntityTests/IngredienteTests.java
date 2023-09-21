package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class IngredienteTests {
    private Ingrediente ingrediente = new Ingrediente();
    private List<Sabor> sabores = new ArrayList<>();
    @BeforeEach
    void setup() {
        sabores.add(new Sabor());
        ingrediente.setId(1L);
        ingrediente.setNome("Ingrediente A");
        ingrediente.setQuantidade(10);
        ingrediente.setSabores(sabores);
    }

    @Test
    void ingredienteIdTest(){
        assertEquals(1L, ingrediente.getId());
    }
    @Test
    void ingredienteNomeTest(){
        assertEquals("Ingrediente A", ingrediente.getNome());
    }
    @Test
    void ingredienteQuantidadeTest(){
        assertEquals(10, ingrediente.getQuantidade());
    }
    @Test
    void ingredienteSaboresTest(){
        assertEquals(sabores, ingrediente.getSabores());
    }
    @Test
    void ingredienteAllArgsConstructorTest(){
        Ingrediente tamanhoAllArgs = new Ingrediente("Ingrediente A", 10, sabores);
        assertThat(ingrediente).usingRecursiveComparison().ignoringFields("id").isEqualTo(tamanhoAllArgs);
    }

}
