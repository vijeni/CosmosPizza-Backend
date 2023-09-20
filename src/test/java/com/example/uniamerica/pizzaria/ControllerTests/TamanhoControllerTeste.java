package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.ProdutoController;
import com.example.uniamerica.pizzaria.Controller.TamanhoController;
import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Service.ProdutoService;
import com.example.uniamerica.pizzaria.Service.TamanhoService;
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
class TamanhoControllerTeste {
    @InjectMocks
    private TamanhoController controller;
    @Mock
    private TamanhoService service;

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest() {
        Long idTamanho = 1L;
        TamanhoDTO tamanho = new TamanhoDTO();
        tamanho.setId(idTamanho);
        when(service.findById(idTamanho)).thenReturn(tamanho);

        ResponseEntity<TamanhoDTO> controllerResponse = controller.findById(idTamanho);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(tamanho, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void getAllTest(){
        TamanhoDTO tamanho = new TamanhoDTO();
        tamanho.setId(1L);
        List<TamanhoDTO> tamanhosList = new ArrayList<>();
        tamanhosList.add(tamanho);
        when(service.getAll()).thenReturn(tamanhosList);
        ResponseEntity<List<TamanhoDTO>> controllerResponse = controller.getAll();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(tamanhosList, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void cadastrarTamanhoTest() {
        TamanhoDTO tamanho = new TamanhoDTO();
        when(service.cadastrar(tamanho)).thenReturn(tamanho);
        ResponseEntity<TamanhoDTO> controllerResponse = controller.cadastrar(tamanho);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(tamanho, controllerResponse.getBody());
    }
    @Test
    void editarTamanhoTest() {
        Long idTamanho = 1L;
        TamanhoDTO tamanho = new TamanhoDTO();
        tamanho.setId(idTamanho);
        when(service.editar(idTamanho, tamanho)).thenReturn(tamanho);
        ResponseEntity<TamanhoDTO> controllerResponse = controller.editar(idTamanho, tamanho);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(tamanho, controllerResponse.getBody());
    }
    @Test
    void deletarTamanhoTest() {
        Long idTamanho = 1L;
        ResponseEntity<String> controllerResponse = controller.deletar(idTamanho);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("O tamanho com o ID 1 foi deletado com sucesso.", controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }

}

