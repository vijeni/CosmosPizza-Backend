package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.dto.*;
import com.example.uniamerica.pizzaria.entity.*;
import com.example.uniamerica.pizzaria.repository.PedidoRepository;
import com.example.uniamerica.pizzaria.repository.PessoaRepository;
import com.example.uniamerica.pizzaria.service.PedidoService;
import com.example.uniamerica.pizzaria.service.PizzaService;
import com.example.uniamerica.pizzaria.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
 class PedidoServiceTests {
    @Mock
    PedidoRepository repository;
    @Mock
    PizzaService pizzaService;
    @Mock
    ProdutoService produtoService;
    @Mock
    PessoaRepository pessoaRepository;
    @InjectMocks
    private PedidoService service;

    PedidoDTO pedidoDTO = new PedidoDTO();
    Pedido pedidoEntity = new Pedido();
    List<PedidoDTO> pedidoDTOList = new ArrayList<>();
    List<Pedido> pedidoEntityList = new ArrayList<>();
    List<PizzaDTO> pizzasDTO = new ArrayList<>();
    List<Pizza> pizzas = new ArrayList<>();
    List<ProdutoDTO> produtoDTOS = new ArrayList<>();
    List<Produto> produtos = new ArrayList<>();
    Pessoa pessoa = new Pessoa();
    PessoaDTO pessoaDTO = new PessoaDTO();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        pessoa.setId(1L);
        pessoaDTO.setId(1L);

        pedidoDTO.setId(1L);
        pedidoDTO.setStatus(Status.AGUARDANDO_CONFIRMACAO);
        pedidoDTO.setValorPedido(10D);
        pedidoDTO.setDataAbertura(LocalDateTime.now());
        pedidoDTO.setEntrega(true);
        pedidoDTO.setFormaPagamento(Pagamento.DEBITO);
        pedidoDTO.setValorEntrega(5D);
        pedidoDTO.setValorTotal(15D);
        pedidoDTO.setDataAbertura(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 0, 0));
        pedidoDTO.setFuncionario(pessoaDTO);
        pedidoDTO.setCliente(pessoaDTO);
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
        pedidoEntity.setFuncionario(pessoa);
        pedidoEntity.setCliente(pessoa);
        pizzas.add(new Pizza());
        produtos.add(new Produto());
        pedidoEntity.setPizzas(pizzas);
        pedidoEntity.setProdutos(produtos);
        pedidoEntityList.add(pedidoEntity);


        when(repository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        when(repository.findAll()).thenReturn(pedidoEntityList);
        when(repository.save(Mockito.any(Pedido.class))).thenReturn(pedidoEntity);
        when(pessoaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(pessoa));
    }
    @Test
    void pedidoDtoToPedidoEntityTest(){
        Pedido pedido = service.toPedido(pedidoDTO);
        assertThat(pedido).usingRecursiveComparison().isEqualTo(pedidoEntity);
    }

    @Test
    void pedidoEntityToPedidoDTOTest(){
        PedidoDTO pedido = service.toPedidoDTO(pedidoEntity);
        assertThat(pedido).usingRecursiveComparison().isEqualTo(this.pedidoDTO);
    }
    @Test
    void pedidoFindByIdTest(){
        PedidoDTO retornoService = service.findById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(pedidoDTO);
    }

    @Test
    void pedidoGetAllTest(){
        List<PedidoDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(pedidoDTOList);
    }

    @Test
    void pedidoCadastrarTest(){
        pedidoDTO.setValorTotal(0D);
        PedidoDTO retornoService = service.cadastrar(pedidoDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(pedidoDTO);
        // Testar se o valor total é calculado corretamente
        assertEquals(15D, retornoService.getValorTotal());
    }
    @Test
    void pedidoEditarTest(){
        PedidoDTO retornoService = service.editar(1L, pedidoDTO);
        assertNotNull(retornoService);
        System.out.println(pedidoDTO.getValorTotal());
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(pedidoDTO);
    }

    @Test
    void pedidoFinalizarTest(){
        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
        when(repository.save(pedidoCaptor.capture())).thenAnswer(invocation -> invocation.<Pedido>getArgument(0));
        PedidoDTO pedidoFinalizado = service.finalizarPedido(1L);
        assertEquals(Status.PRONTO, pedidoFinalizado.getStatus());
    }

    @Test
    void pedidoDeletarTest() {
        service.deletar(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
