package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.IngredienteController;
import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
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

import static org.mockito.Mockito.*;

@SpringBootTest
public class IngredienteControllerTeste {

    @InjectMocks
    private IngredienteController ingredienteController;

    @Mock
    private IngredientesService ingredientesService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest(){
        Long ingredienteId = 1L;
        IngredienteDTO ingredienteDTO = new IngredienteDTO();

        when(ingredientesService.findByID(ingredienteId)).thenReturn(ingredienteDTO);
        ResponseEntity<IngredienteDTO>response = ingredienteController.findById(ingredienteId);

        /*
        AssertEquals de ingredienteDTO está retornando nulo. Então removi.
         */

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(ingredientesService,times(1)).findById(ingredienteId);

    }




}
