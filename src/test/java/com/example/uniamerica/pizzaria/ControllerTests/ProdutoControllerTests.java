package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.ProdutoController;
import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import com.example.uniamerica.pizzaria.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoControllerTests {
    @InjectMocks
    private ProdutoController controller;

    @Mock
    private ProdutoService service;

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest() {
        Long idProduto = 1L;
        ProdutoDTO produto = new ProdutoDTO();
        produto.setId(idProduto);
        when(service.findById(idProduto)).thenReturn(produto);

        ResponseEntity<ProdutoDTO> controllerResponse = controller.findById(idProduto);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(produto, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void getAllTest(){
        ProdutoDTO produto = new ProdutoDTO();
        produto.setId(1L);
        List<ProdutoDTO> produtosList = new ArrayList<>();
        produtosList.add(produto);
        when(service.getAll()).thenReturn(produtosList);
        ResponseEntity<List<ProdutoDTO>> controllerResponse = controller.getAll();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(produtosList, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
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

