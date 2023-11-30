package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.PedidoController;
import com.example.uniamerica.pizzaria.dto.*;
import com.example.uniamerica.pizzaria.entity.*;
import com.example.uniamerica.pizzaria.repository.PedidoRepository;
import com.example.uniamerica.pizzaria.repository.ClienteRepository;
import com.example.uniamerica.pizzaria.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PedidoControllerTests {
    @Autowired
    private PedidoController controller;
    @Autowired
    private PedidoService service;
    @MockBean
    private PedidoRepository repository;
    @MockBean
    private PizzaService pizzaService;
    @MockBean
    private ProdutoService produtoService;
    @MockBean
    private ClienteRepository clienteRepository;
    PedidoDTO pedidoDTO = new PedidoDTO();
    Pedido pedidoEntity = new Pedido();
    List<PedidoDTO> pedidoDTOList = new ArrayList<>();
    List<Pedido> pedidoEntityList = new ArrayList<>();
    List<PizzaDTO> pizzasDTO = new ArrayList<>();
    List<Pizza> pizzas = new ArrayList<>();
    List<ProdutoDTO> produtoDTOS = new ArrayList<>();
    List<Produto> produtos = new ArrayList<>();
    Cliente cliente = new Cliente();
    ClienteDTO clienteDTO = new ClienteDTO();
    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
        cliente.setId(1L);
        clienteDTO.setId(1L);
        pedidoDTO.setId(1L);
        pedidoDTO.setStatus(Status.AGUARDANDO_CONFIRMACAO);
        pedidoDTO.setValorPedido(10D);
        pedidoDTO.setDataAbertura(LocalDateTime.now());
        pedidoDTO.setEntrega(true);
        pedidoDTO.setFormaPagamento(Pagamento.DEBITO);
        pedidoDTO.setValorEntrega(5D);
        pedidoDTO.setValorTotal(15D);
        pedidoDTO.setDataAbertura(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 0, 0));
        pedidoDTO.setFuncionario(clienteDTO);
        pedidoDTO.setCliente(clienteDTO);
        pizzasDTO.add(new PizzaDTO());
        produtoDTOS.add(new ProdutoDTO());
        pedidoDTO.setPizzas(pizzasDTO);
        pedidoDTO.setProdutos(produtoDTOS);
        pedidoDTOList.add(pedidoDTO);

        pedidoEntity.setId(1L);
        pedidoEntity.setStatus(Status.AGUARDANDO_CONFIRMACAO);
        pedidoEntity.setValorPedido(10D);
        pedidoEntity.setEntrega(true);
        pedidoEntity.setFormaPagamento(Pagamento.DEBITO);
        pedidoEntity.setValorEntrega(5D);
        pedidoEntity.setValorTotal(15D);
        pedidoEntity.setDataAbertura(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 0, 0));
        pedidoEntity.setFuncionario(cliente);
        pedidoEntity.setCliente(cliente);
        pizzas.add(new Pizza());
        produtos.add(new Produto());
        pedidoEntity.setPizzas(pizzas);
        pedidoEntity.setProdutos(produtos);
        pedidoEntityList.add(pedidoEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        when(pizzaService.findById(1L)).thenReturn(new PizzaDTO());
        when(produtoService.findById(1L)).thenReturn(new ProdutoDTO());
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        when(repository.findAll()).thenReturn(pedidoEntityList);
        when(repository.save(Mockito.any(Pedido.class))).thenReturn(pedidoEntity);


    }

    @Test
    void findByIdTest() {
        ResponseEntity<PedidoDTO> controllerResponse = controller.findById(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(pedidoDTO);
    }
    @Test
    void getAllTest(){
        ResponseEntity<List<PedidoDTO>> controllerResponse = controller.getAll();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(pedidoDTOList);
    }
    @Test
    void finalizarPedidoTest() {
        ResponseEntity<PedidoDTO> controllerResponse = controller.finalizarPedido(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(pedidoDTO);
    }
    @Test
    void cadastrarPedidoTest() {
        ResponseEntity<PedidoDTO> controllerResponse = controller.cadastrar(pedidoDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(pedidoDTO);
    }
    @Test
    void editarPedidoTest() {
        ResponseEntity<PedidoDTO> controllerResponse = controller.editar(1L, pedidoDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(pedidoDTO);
    }
    @Test
    void deletarPedidoTest() {
        ResponseEntity<String> controllerResponse = controller.deletar(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("Pedido com ID 1 foi deletado com sucesso!", controllerResponse.getBody());
    }

}

