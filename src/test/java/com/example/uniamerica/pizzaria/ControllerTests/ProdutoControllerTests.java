package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.ProdutoController;
import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import com.example.uniamerica.pizzaria.entity.Produto;
import com.example.uniamerica.pizzaria.repository.ProdutoRepository;
import com.example.uniamerica.pizzaria.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
        when(repository.findByDescricaoLike(Mockito.any(String.class))).thenReturn(produtoEntityList);
        when(repository.save(Mockito.any(Produto.class))).thenReturn(produtoEntity);

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
        ResponseEntity<List<ProdutoDTO>> controllerResponse = controller.getAllByNome("Produto A");
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(produtoDTOList);
    }
    @Test
    void cadastrarProdutoTest() {
        ResponseEntity<ProdutoDTO> controllerResponse = controller.cadastrar(produtoDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(produtoEntity);
    }
    @Test
    void editarProdutoTest() {
        ResponseEntity<ProdutoDTO> controllerResponse = controller.editar(1L, produtoDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(produtoEntity);
    }
//    @Test
//    void deletarProdutoTest() {
//        ResponseEntity<String> controllerResponse = controller.deletar(1L);
//        assertEquals("Produto com ID 1 foi deletado com sucesso!", controllerResponse.getBody());
//    }

}

