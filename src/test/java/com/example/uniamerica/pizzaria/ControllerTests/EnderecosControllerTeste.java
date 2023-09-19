package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.EnderecoController;
import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.Service.EnderecosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EnderecosControllerTeste {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecosService enderecosService; //Nota mental: O @InjectMocks não funciona em Service.

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        Long enderecoId = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        when(enderecosService.findById(enderecoId)).thenReturn(enderecoDTO);

        ResponseEntity<EnderecoDTO> response = enderecoController.findById(enderecoId);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(enderecoDTO,response.getBody());
        verify(enderecosService,times(1)).findById(enderecoId);
    }

    @Test
    void getAll(){
        List<EnderecoDTO>enderecoDTOList = new ArrayList<>();
        when(enderecosService.listAll()).thenReturn(enderecoDTOList);

        ResponseEntity<List<EnderecoDTO>> response = enderecoController.findAll();
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(enderecoDTOList,response.getBody());
        verify(enderecosService,times(1)).listAll();
    }


}
