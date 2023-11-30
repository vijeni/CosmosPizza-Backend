package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.entity.*;
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
 class PedidoTests {
    private Pedido pedido = new Pedido();
    private Cliente cliente = new Cliente();
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
//        pedido.setFuncionario(cliente);
        pedido.setCliente(cliente);
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
        assertEquals(cliente, pedido.getCliente());
    }
    @Test
    void pedidoFuncionarioTest(){
        assertEquals(cliente, pedido.getFuncionario());
    }
    @Test
    void pedidoPizzasTest(){
        assertEquals(pizzas, pedido.getPizzas());
    }
    @Test
    void pedidoProdutosTest(){
        assertEquals(produtos, pedido.getProdutos());
    }
//    @Test
//    void pedidoAllArgsConstructorTest(){
//        Pedido produtoAllArgs = new Pedido(cliente, cliente, Status.AGUARDANDO_CONFIRMACAO, 10D, 5D,  15D, Pagamento.DEBITO, true, data, data, produtos, pizzas);
//        assertThat(pedido).usingRecursiveComparison().ignoringFields("id", "dataConclusao").isEqualTo(produtoAllArgs);
//    }
}
