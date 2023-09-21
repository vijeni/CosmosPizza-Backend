package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Entity.Pizza;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaborTests {
    private Sabor sabor = new Sabor();
    private List<Ingrediente> ingredientes = new ArrayList<>();
    private List<Pizza> pizzas = new ArrayList<>();

    @BeforeEach
    void setup() {
        ingredientes.add(new Ingrediente());
        pizzas.add(new Pizza());
        sabor.setId(1L);
        sabor.setNome("Sabor A");
        sabor.setDescricao("Descricao");
        sabor.setIngredientes(ingredientes);
        sabor.setPizzas(pizzas);

    }

    @Test
    void saborIdTest(){
        assertEquals(1L, sabor.getId());
    }

    @Test
    void saborNomeTest(){
        assertEquals("Sabor A", sabor.getNome());
    }
    @Test
    void saborDescricaoTest(){
        assertEquals("Descricao", sabor.getDescricao());
    }
    @Test
    void saborIngredientesTest(){
        assertEquals(ingredientes, sabor.getIngredientes());
    }
    @Test
    void saborPizzasTest(){
        assertEquals(pizzas, sabor.getPizzas());
    }
    @Test
    void saborAllArgsConstructorTest(){
        Sabor tamanhoAllArgs = new Sabor("Sabor A", "Descricao", ingredientes, pizzas);
        assertThat(sabor).usingRecursiveComparison().ignoringFields("id").isEqualTo(tamanhoAllArgs);
    }

}
