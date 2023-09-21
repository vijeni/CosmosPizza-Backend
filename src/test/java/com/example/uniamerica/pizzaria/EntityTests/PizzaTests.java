package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Pedido;
import com.example.uniamerica.pizzaria.Entity.Pizza;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class PizzaTests {
    private Pizza pizza;
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Sabor> sabores = new ArrayList<>();
    private Tamanho tamanho = new Tamanho();


    @BeforeEach
    void setup(){
        sabores.add(new Sabor());
        pedidos.add(new Pedido());
        tamanho.setId(1L);
        tamanho.setMaximoSabores(1);
        tamanho.setValor(10D);
        tamanho.setTamanho("Mini");

        pizza = new Pizza();
        pizza.setId(1L);
        pizza.setObservacao("Observacao X");
        pizza.setSabores(sabores);
        pizza.setTamanho(tamanho);
        pizza.setPedidos(pedidos);
    }

    @Test
    void pizzaIdTest(){
        assertEquals(1L, pizza.getId());
    }
    @Test
    void pizzaObservacaoTest(){
        assertEquals("Observacao X", pizza.getObservacao());
    }
    @Test
    void pizzaSaboresTest(){
        assertEquals(sabores, pizza.getSabores());
    }
    @Test
    void pizzaTamanhoTest(){
        assertEquals(tamanho, pizza.getTamanho());
    }
    @Test
    void pizzaPedidosTest(){
        assertEquals(pedidos, pizza.getPedidos());
    }
    @Test
    void pizzaAllArgsConstructorTest(){
        Pizza pizzaAllArgs = new Pizza(sabores, tamanho, "Observacao X", pedidos);
        assertThat(pizza).usingRecursiveComparison().ignoringFields("id").isEqualTo(pizzaAllArgs);
    }
}
