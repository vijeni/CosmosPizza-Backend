package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.Entity.Pedido;
import com.example.uniamerica.pizzaria.Entity.Produto;
import com.example.uniamerica.pizzaria.Repository.ProdutoRepository;
import com.example.uniamerica.pizzaria.Service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProdutoServiceTests {
    @Mock
    ProdutoRepository repository;
    @InjectMocks
    private ProdutoService service;

    ProdutoDTO produtoDTO = new ProdutoDTO();
    Produto produtoEntity = new Produto();
    List<ProdutoDTO> produtoDTOList = new ArrayList<>();
    List<Produto> produtoEntityList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        pedidoDTOS.add(new PedidoDTO());
        produtoDTO.setId(1L);
        produtoDTO.setNome("Nome");
        produtoDTO.setQuantidadeEstoque(10);
        produtoDTO.setDescricao("Descrição");
        produtoDTO.setValorUnitario(10D);
        produtoDTOList.add(produtoDTO);

        List<Pedido> pedidosEntity = new ArrayList<>();
        pedidosEntity.add(new Pedido());
        produtoEntity.setId(1L);
        produtoEntity.setNome("Nome");
        produtoEntity.setQuantidadeEstoque(10);
        produtoEntity.setDescricao("Descrição");
        produtoEntity.setValorUnitario(10D);
        produtoEntityList.add(produtoEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(produtoEntity));
        when(repository.findAll()).thenReturn(produtoEntityList);
        when(repository.save(Mockito.any(Produto.class))).thenReturn(produtoEntity);
    }
    @Test
    void produtoDTO_ToTamanhoEntity_Test(){
        Produto produto = service.toProduto(produtoDTO);
        assertThat(produto).usingRecursiveComparison().isEqualTo(produtoEntity);
    }

    @Test
    void produtoEntity_toProdutoDTO_Test(){
        ProdutoDTO produtoDTO = service.toProdutoDTO(produtoEntity);
        assertThat(produtoDTO).usingRecursiveComparison().isEqualTo(this.produtoDTO);
    }
    @Test
    void produtoFindByIdTest(){
        ProdutoDTO retornoService = service.findById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(produtoDTO);
    }

    @Test
    void produtoGetAllTest(){
        List<ProdutoDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(produtoDTOList);
    }

    @Test
    void produtoCadastrarTest(){
        ProdutoDTO produtoCadastrar = new ProdutoDTO();
        produtoCadastrar.setNome("Nome");
        produtoCadastrar.setQuantidadeEstoque(10);
        produtoCadastrar.setDescricao("Descrição");
        produtoCadastrar.setValorUnitario(10D);

        ProdutoDTO retornoService = service.cadastrar(produtoCadastrar);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(produtoDTO);
    }
    @Test
    void produtoEditarTest(){
        ProdutoDTO retornoService = service.editar(1L, produtoDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(produtoDTO);
    }
}
