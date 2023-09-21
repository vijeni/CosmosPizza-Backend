package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.TamanhoController;
import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.TamanhoRepository;
import com.example.uniamerica.pizzaria.Service.TamanhoService;
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
class TamanhoControllerTests {
    @Autowired
    private TamanhoController controller;
    @Autowired
    private TamanhoService service;
    @MockBean
    private TamanhoRepository repository;
    private TamanhoDTO tamanhoDTO = new TamanhoDTO();
    private Tamanho tamanhoEntity = new Tamanho();
    List<TamanhoDTO> tamanhoDTOList = new ArrayList<>();
    List<Tamanho> tamanhoEntityList = new ArrayList<>();

    @BeforeEach
    void setupMocks() {
        tamanhoDTO.setId(1L);
        tamanhoDTO.setTamanho("Média");
        tamanhoDTO.setMaximoSabores(2);
        tamanhoDTO.setValor(10D);
        tamanhoDTOList.add(tamanhoDTO);

        tamanhoEntity.setId(1L);
        tamanhoEntity.setTamanho("Média");
        tamanhoEntity.setMaximoSabores(2);
        tamanhoEntity.setValor(10D);
        tamanhoEntityList.add(tamanhoEntity);

        MockitoAnnotations.openMocks(this);
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(tamanhoEntity));
        when(repository.findAll()).thenReturn(tamanhoEntityList);
        when(repository.save(Mockito.any(Tamanho.class))).thenReturn(tamanhoEntity);
    }

    @Test
    void findByIdTest() {
        ResponseEntity<TamanhoDTO> controllerResponse = controller.findById(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tamanhoDTO);
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void getAllTest(){
        ResponseEntity<List<TamanhoDTO>> controllerResponse = controller.getAll();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tamanhoDTOList);
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void cadastrarTamanhoTest() {
        ResponseEntity<TamanhoDTO> controllerResponse = controller.cadastrar(tamanhoDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tamanhoDTO);
    }
    @Test
    void editarTamanhoTest() {
        ResponseEntity<TamanhoDTO> controllerResponse = controller.editar(1L, tamanhoDTO);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertThat(controllerResponse.getBody()).usingRecursiveComparison().isEqualTo(tamanhoDTO);
    }
    @Test
    void deletarTamanhoTest() {
        ResponseEntity<String> controllerResponse = controller.deletar(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("O tamanho com o ID 1 foi deletado com sucesso.", controllerResponse.getBody());
    }

}

