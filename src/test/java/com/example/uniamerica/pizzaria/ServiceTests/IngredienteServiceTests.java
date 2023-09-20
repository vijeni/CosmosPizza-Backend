package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.Controller.IngredienteController;
import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class IngredienteServiceTests {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredientesService ingredientesService;

    private IngredienteDTO ingredienteDTO = new IngredienteDTO();
    private Ingrediente ingrediente = new Ingrediente();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void ingredienteFindByIdTest(){
        Mockito.when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(ingrediente));

        IngredienteDTO result = ingredientesService.findByID(1L);

        Assertions.assertNotNull(result);

        Mockito.verify(ingredienteRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void ingredientePostTest(){

    }




}
