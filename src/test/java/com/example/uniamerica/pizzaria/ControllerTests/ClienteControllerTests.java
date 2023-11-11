package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.PessoaController;
import com.example.uniamerica.pizzaria.dto.PessoaDTO;
import com.example.uniamerica.pizzaria.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteControllerTests {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void pessoaGetIdTest(){
        Long pessoaId = 1L;
        PessoaDTO pessoaDTO = new PessoaDTO();

        when(pessoaService.findById(pessoaId)).thenReturn(pessoaDTO);
        ResponseEntity<PessoaDTO>response = pessoaController.findById(pessoaId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(pessoaDTO,response.getBody());

        Mockito.verify(pessoaService,times(1)).findById(pessoaId);

    }

    @Test
    void pessoaGetAllTest(){
        List<PessoaDTO> pessoaDTOList = new ArrayList<>();

        when(pessoaService.getAll()).thenReturn(pessoaDTOList);
        ResponseEntity<List<PessoaDTO>>response = pessoaController.getAll();

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(pessoaDTOList,response.getBody());
        verify(pessoaService,times(1)).getAll();
    }

    @Test
    void pessoaPutTest(){
        PessoaDTO pessoaDTO = new PessoaDTO();
        Long pessoaId = 1L;

        when(pessoaService.put(pessoaDTO,pessoaId)).thenReturn(pessoaDTO);
        ResponseEntity<PessoaDTO>response = pessoaController.atualizar(pessoaDTO,pessoaId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(pessoaDTO,response.getBody());
        verify(pessoaService,times(1)).put(pessoaDTO,pessoaId);
    }

    @Test
    void pessoaPostTest(){
        PessoaDTO pessoaDTO = new PessoaDTO();
        when(pessoaService.post(pessoaDTO)).thenReturn(pessoaDTO);

        ResponseEntity<PessoaDTO>response = pessoaController.cadastrar(pessoaDTO);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(pessoaDTO,response.getBody());
        verify(pessoaService,times(1)).post(pessoaDTO);
    }

    @Test
    void pessoaDeleteTest(){
        Long pessoaId = 1L;

        ResponseEntity<String> response = pessoaController.deletar(pessoaId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(pessoaService,times(1)).desativar(pessoaId);
    }

}
