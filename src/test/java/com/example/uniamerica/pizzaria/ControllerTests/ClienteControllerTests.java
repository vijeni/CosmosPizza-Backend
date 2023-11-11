package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.ClienteController;
import com.example.uniamerica.pizzaria.dto.ClienteDTO;
import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.service.ClienteService;
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
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void pessoaGetIdTest(){
        Long pessoaId = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();

        when(clienteService.findById(pessoaId)).thenReturn(clienteDTO);
        ResponseEntity<ClienteDTO>response = clienteController.findById(pessoaId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(clienteDTO,response.getBody());

        Mockito.verify(clienteService,times(1)).findById(pessoaId);

    }

    @Test
    void pessoaGetAllTest(){
        List<ClienteDTO> clienteDTOList = new ArrayList<>();

        when(clienteService.getAll()).thenReturn(clienteDTOList);
        ResponseEntity<List<ClienteDTO>>response = clienteController.getAll();

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(clienteDTOList,response.getBody());
        verify(clienteService,times(1)).getAll();
    }

    @Test
    void pessoaPutTest(){
        ClienteDTO clienteDTO = new ClienteDTO();
        Long pessoaId = 1L;

        when(clienteService.put(clienteDTO,pessoaId)).thenReturn(clienteDTO);
        ResponseEntity<ClienteDTO>response = clienteController.atualizar(clienteDTO,pessoaId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(clienteDTO,response.getBody());
        verify(clienteService,times(1)).put(clienteDTO,pessoaId);
    }

    @Test
    void pessoaPostTest(){
        ClienteDTO clienteDTO = new ClienteDTO();
        when(clienteService.post(clienteDTO)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO>response = clienteController.cadastrar(clienteDTO);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(clienteDTO,response.getBody());
        verify(clienteService,times(1)).post(clienteDTO);
    }

    @Test
    void pessoaDeleteTest(){
        Long pessoaId = 1L;


        ResponseEntity<String> response = clienteController.desativar(pessoaId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(clienteService,times(1)).desativar(pessoaId);
    }

}
