package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.SaborController;
import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Service.SaborService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SaborControllerTeste {

    @InjectMocks
    private SaborController saborController;

    @Mock
    private SaborService saborService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void SaborGetById(){
        Long id = 1L;
        SaborDTO saborDTO = new SaborDTO();
        when(saborService.findById(id)).thenReturn(saborDTO);

        ResponseEntity<SaborDTO>response = saborController.findById(id);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(saborDTO,response.getBody());
        verify(saborService,times(1)).findById(id);

    }





}
