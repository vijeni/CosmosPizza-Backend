package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.dto.PedidoDTO;
import com.example.uniamerica.pizzaria.dto.PizzaDTO;
import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class PizzaDTOTests {
    private PizzaDTO pizzaDTO;
    private List<PedidoDTO> pedidos = new ArrayList<>();
    private List<SaborDTO> sabores = new ArrayList<>();
    private TamanhoDTO tamanho = new TamanhoDTO();


    @BeforeEach
    void setup(){
        sabores.add(new SaborDTO());
        pedidos.add(new PedidoDTO());
        tamanho.setId(1L);
        tamanho.setMaximoSabores(1);
        tamanho.setValor(10D);
        tamanho.setNome("Mini");

        pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(1L);
        pizzaDTO.setObservacao("Observacao X");
        pizzaDTO.setSabores(sabores);
        pizzaDTO.setTamanho(tamanho);
        pizzaDTO.setPedidos(pedidos);
    }

    @Test
    void pizzaIdTest(){
        assertEquals(1L, pizzaDTO.getId());
    }
    @Test
    void pizzaObservacaoTest(){
        assertEquals("Observacao X", pizzaDTO.getObservacao());
    }
    @Test
    void pizzaSaboresTest(){
        assertEquals(sabores, pizzaDTO.getSabores());
    }
    @Test
    void pizzaTamanhoTest(){
        assertEquals(tamanho, pizzaDTO.getTamanho());
    }
    @Test
    void pizzaPedidosTest(){
        assertEquals(pedidos, pizzaDTO.getPedidos());
    }
    @Test
    void pizzaAllArgsConstructorTest(){
        PizzaDTO pizzaAllArgs = new PizzaDTO(sabores, tamanho, "Observacao X", pedidos);
        assertThat(pizzaDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(pizzaAllArgs);
    }
}
