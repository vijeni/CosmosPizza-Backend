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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IngredienteServiceTests {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredientesService ingredientesService;


    private IngredienteDTO ingredienteDTO;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setNome("nome");
        ingredienteDTO.setQuantidade(5);
    }

    @Test
    void ingredienteFindByIdTest(){
        Ingrediente ingredienteEntidade = new Ingrediente();
        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(ingredienteEntidade));

        IngredienteDTO result = ingredientesService.findByID(1L);

        Assertions.assertNotNull(result);

        Mockito.verify(ingredienteRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void ingredientePostTest(){
        Ingrediente ingredienteEntidade = new Ingrediente();
        ingredienteEntidade.setNome("nome");
        ingredienteEntidade.setQuantidade(5);

        when(ingredienteRepository.save(Mockito.any(Ingrediente.class))).thenReturn(ingredienteEntidade);

        IngredienteDTO result = ingredientesService.post(ingredienteDTO);

        Mockito.verify(ingredienteRepository,times(1)).save(Mockito.any(Ingrediente.class));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("nome",result.getNome());
        Assertions.assertEquals(5,result.getQuantidade());
    }




}
