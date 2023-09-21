package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.ProdutoController;
import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import com.example.uniamerica.pizzaria.entity.Produto;
import com.example.uniamerica.pizzaria.repository.ProdutoRepository;
import com.example.uniamerica.pizzaria.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoControllerTests {
    @Autowired
    private ProdutoController controller;

    @Autowired
    private ProdutoService service;
    @MockBean
    private ProdutoRepository repository;
    ProdutoDTO produtoDTO = new ProdutoDTO();
    Produto produtoEntity = new Produto();
    List<ProdutoDTO> produtoDTOList = new ArrayList<>();
    List<Produto> produtoEntityList = new ArrayList<>();



    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
        produtoDTO.setId(1L);
        produtoDTOList.add(produtoDTO);
        produtoEntity.setId(1L);
        produtoEntityList.add(produtoEntity);

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(produtoEntity));
        when(repository.findAll()).thenReturn(produtoEntityList);

    }

    @Test
    void findByIdTest() {
        ResponseEntity<ProdutoDTO> controllerResponse = controller.findById(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(produtoEntity);
    }
    @Test
    void getAllTest(){
        ResponseEntity<List<ProdutoDTO>> controllerResponse = controller.getAll();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(produtoDTOList);
    }
    @Test
    void getAllByNome(){
        ProdutoDTO produto = new ProdutoDTO();
        produto.setId(1L);
        produto.setNome("Produto A");
        List<ProdutoDTO> produtosList = new ArrayList<>();
        produtosList.add(produto);
        when(service.getAllByNome("Produto A")).thenReturn(produtosList);
        ResponseEntity<List<ProdutoDTO>> controllerResponse = controller.getAllByNome("Produto A");
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(produtosList, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void cadastrarProdutoTest() {
        ProdutoDTO produto = new ProdutoDTO();
        when(service.cadastrar(produto)).thenReturn(produto);
        ResponseEntity<ProdutoDTO> controllerResponse = controller.cadastrar(produto);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(produto, controllerResponse.getBody());
    }
    @Test
    void editarProdutoTest() {
        Long idProduto = 1L;
        ProdutoDTO produto = new ProdutoDTO();
        produto.setId(idProduto);
        when(service.editar(idProduto, produto)).thenReturn(produto);
        ResponseEntity<ProdutoDTO> controllerResponse = controller.editar(idProduto, produto);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(produto, controllerResponse.getBody());
    }
    @Test
    void deletarProdutoTest() {
        Long idProduto = 1L;
        ResponseEntity<String> controllerResponse = controller.deletar(idProduto);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("Produto com ID 1 foi deletado com sucesso!", controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }

}

