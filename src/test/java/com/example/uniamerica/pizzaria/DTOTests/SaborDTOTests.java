package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaborDTOTests {
    private SaborDTO saborDTO = new SaborDTO();
    private List<IngredienteDTO> ingredientes = new ArrayList<>();
    private List<PizzaDTO> pizzas = new ArrayList<>();

    @BeforeEach
    void setup() {
        ingredientes.add(new IngredienteDTO());
        saborDTO.setId(1L);
        saborDTO.setNome("Sabor A");
        saborDTO.setDescricao("Descricao");
        saborDTO.setIngredientes(ingredientes);
        saborDTO.setPizzas(pizzas);

    }

    @Test
    void saborIdTest(){
        assertEquals(1L, saborDTO.getId());
    }

    @Test
    void saborNomeTest(){
        assertEquals("Sabor A", saborDTO.getNome());
    }
    @Test
    void saborDescricaoTest(){
        assertEquals("Descricao", saborDTO.getDescricao());
    }
    @Test
    void saborIngredientesTest(){
        assertEquals(ingredientes, saborDTO.getIngredientes());
    }
    @Test
    void saborPizzasTest(){
        assertEquals(pizzas, saborDTO.getPizzas());
    }
    @Test
    void saborAllArgsConstructorTest(){
        SaborDTO tamanhoAllArgs = new SaborDTO("Sabor A", "Descricao", ingredientes, pizzas);
        assertThat(saborDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(tamanhoAllArgs);
    }

}
