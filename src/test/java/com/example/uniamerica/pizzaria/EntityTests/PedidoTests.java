package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.Entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PedidoTests {
    private Pedido pedido = new Pedido();
    private Pessoa pessoa = new Pessoa();
    private List<Pizza> pizzas = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private LocalDateTime data = LocalDateTime.of(2023, Month.SEPTEMBER, 20, 0, 0);
    @BeforeEach
    void setup(){
        pedido.setId(1L);
        pedido.setStatus(Status.AGUARDANDO_CONFIRMACAO);
        pedido.setValorPedido(10D);
        pedido.setEntrega(true);
        pedido.setFormaPagamento(Pagamento.DEBITO);
        pedido.setValorEntrega(5D);
        pedido.setValorTotal(15D);
        pedido.setDataAbertura(data);
        pedido.setFuncionario(pessoa);
        pedido.setCliente(pessoa);
        pizzas.add(new Pizza());
        produtos.add(new Produto());
        pedido.setPizzas(pizzas);
        pedido.setProdutos(produtos);
    }

    @Test
    void pedidoIdTest(){
        assertEquals(1L, pedido.getId());
    }

    @Test
    void pedidoStatusTest(){
        assertEquals(Status.AGUARDANDO_CONFIRMACAO, pedido.getStatus());
    }
    @Test
    void pedidoValorPedidoTest(){
        assertEquals(10, pedido.getValorPedido());
    }
    @Test
    void pedidoValorEntregaTest(){
        assertEquals(5D, pedido.getValorEntrega());
    }
    @Test
    void pedidoValorTotalTest(){
        assertEquals(15D, pedido.getValorTotal());
    }
    @Test
    void pedidoClienteTest(){
        assertEquals(pessoa, pedido.getCliente());
    }
    @Test
    void pedidoFuncionarioTest(){
        assertEquals(pessoa, pedido.getFuncionario());
    }
    @Test
    void pedidoPizzasTest(){
        assertEquals(pizzas, pedido.getPizzas());
    }
    @Test
    void pedidoProdutosTest(){
        assertEquals(produtos, pedido.getProdutos());
    }
    @Test
    void pedidoAllArgsConstructorTest(){
        Pedido produtoAllArgs = new Pedido(pessoa, pessoa, Status.AGUARDANDO_CONFIRMACAO, 10D, 5D,  15D, Pagamento.DEBITO, true, data, data, produtos, pizzas);
        assertThat(pedido).usingRecursiveComparison().ignoringFields("id", "dataConclusao").isEqualTo(produtoAllArgs);
    }
}
