package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.PessoaController;
import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Service.PessoaService;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoaControllerTeste {

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



}
