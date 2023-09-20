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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class SaborControllerTests {

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

    @Test
    void saborGetAll(){
        List<SaborDTO> saborDTOList = new ArrayList<>();
        when(saborService.getAll()).thenReturn(saborDTOList);

        ResponseEntity<List<SaborDTO>>response = saborController.getAll();
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(saborDTOList,response.getBody());
        verify(saborService,times(1)).getAll();
    }

    @Test
    void saborPost(){
        SaborDTO saborDTO = new SaborDTO();
        when(saborService.cadastrar(saborDTO)).thenReturn(saborDTO);

        ResponseEntity<SaborDTO>response = saborController.cadastrar(saborDTO);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(saborDTO,response.getBody());
        verify(saborService,times(1)).cadastrar(saborDTO);
    }

    @Test
    void saborPutTeste(){
        Long saborId = 1L;
        SaborDTO saborDTO = new SaborDTO();

        when(saborService.update(saborDTO,saborId)).thenReturn(saborDTO);
        ResponseEntity<SaborDTO>response = saborController.editar(saborDTO,saborId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(saborDTO,response.getBody());
        verify(saborService,times(1)).update(saborDTO,saborId);
    }

    @Test
    void saborDeleteTeste(){
        Long saborId = 1L;

        ResponseEntity<String>response = saborController.deletar(saborId);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(saborService,times(1)).delete(saborId);

    }
}
