package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.dto.*;
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
 class PedidoDTOTests {
    private PedidoDTO pedidoDTO = new PedidoDTO();
    private ClienteDTO clienteDTO = new ClienteDTO();
    private List<PizzaDTO> pizzasDTO = new ArrayList<>();
    private List<ProdutoDTO> produtoDTOS = new ArrayList<>();
    private LocalDateTime data = LocalDateTime.of(2023, Month.SEPTEMBER, 20, 0, 0);
    @BeforeEach
    void setup(){
        pedidoDTO.setId(1L);
        pedidoDTO.setStatus(Status.AGUARDANDO_CONFIRMACAO);
        pedidoDTO.setValorPedido(10D);
        pedidoDTO.setEntrega(true);
        pedidoDTO.setFormaPagamento(Pagamento.DEBITO);
        pedidoDTO.setValorEntrega(5D);
        pedidoDTO.setValorTotal(15D);
        pedidoDTO.setDataAbertura(data);
//        pedidoDTO.setFuncionario(clienteDTO);
        pedidoDTO.setCliente(clienteDTO);
        pizzasDTO.add(new PizzaDTO());
        produtoDTOS.add(new ProdutoDTO());
        pedidoDTO.setPizzas(pizzasDTO);
        pedidoDTO.setProdutos(produtoDTOS);
    }

    @Test
    void pedidoIdTest(){
        assertEquals(1L, pedidoDTO.getId());
    }

    @Test
    void pedidoStatusTest(){
        assertEquals(Status.AGUARDANDO_CONFIRMACAO, pedidoDTO.getStatus());
    }
    @Test
    void pedidoValorPedidoTest(){
        assertEquals(10, pedidoDTO.getValorPedido());
    }
    @Test
    void pedidoValorEntregaTest(){
        assertEquals(5D, pedidoDTO.getValorEntrega());
    }
    @Test
    void pedidoValorTotalTest(){
        assertEquals(15D, pedidoDTO.getValorTotal());
    }
    @Test
    void pedidoClienteTest(){
        assertEquals(clienteDTO, pedidoDTO.getCliente());
    }
    @Test
    void pedidoFuncionarioTest(){
        assertEquals(clienteDTO, pedidoDTO.getFuncionario());
    }
    @Test
    void pedidoPizzasTest(){
        assertEquals(pizzasDTO, pedidoDTO.getPizzas());
    }
    @Test
    void pedidoProdutosTest(){
        assertEquals(produtoDTOS, pedidoDTO.getProdutos());
    }
//    @Test
//    void pedidoAllArgsConstructorTest(){
//        PedidoDTO produtoAllArgs = new PedidoDTO(clienteDTO, clienteDTO, Status.AGUARDANDO_CONFIRMACAO, true, 10D, data, data, 5D, 15D, Pagamento.DEBITO, produtoDTOS, pizzasDTO);
//        assertThat(pedidoDTO).usingRecursiveComparison().ignoringFields("id", "dataConclusao").isEqualTo(produtoAllArgs);
//    }
}
