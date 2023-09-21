package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
import org.junit.Assert;
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
import static org.mockito.Mockito.*;

@SpringBootTest
 class IngredienteServiceTests {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredientesService ingredientesService;


    private IngredienteDTO ingredienteDTO;
    private Sabor sabor;
    private List<IngredienteDTO> ingredienteDTOList = new ArrayList<IngredienteDTO>();
    private Ingrediente ingredienteEntidade = new Ingrediente();


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setId(1L);
        ingredienteDTO.setNome("nome");
        ingredienteDTO.setQuantidade(5);


        ingredienteEntidade.setId(1L);
        ingredienteEntidade.setNome("nome");
        ingredienteEntidade.setQuantidade(5);

        ingredienteDTOList.add(ingredienteDTO);

        when(ingredienteRepository.findAll()).thenReturn(ingredienteDTOList.stream().map(ingredienteDTO -> ingredientesService.toIngredienteEntidade(ingredienteDTO)).toList());
        when(ingredienteRepository.save(Mockito.any(Ingrediente.class))).thenReturn(ingredienteEntidade);
        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(ingredienteEntidade));

    }
    @Test
    void ingredienteDtoToingredienteEntityTest(){
        Ingrediente ingrediente = ingredientesService.toIngredienteEntidade(ingredienteDTO);
        org.assertj.core.api.Assertions.assertThat(ingrediente).usingRecursiveComparison().isEqualTo(ingredienteEntidade);
    }
    @Test
    void ingredienteFindByIdTest(){

        IngredienteDTO result = ingredientesService.findByID(1L);

        Assertions.assertNotNull(result);

        Mockito.verify(ingredienteRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void ingredientePostTest(){

        IngredienteDTO result = ingredientesService.post(ingredienteDTO);

        Mockito.verify(ingredienteRepository,times(1)).save(Mockito.any(Ingrediente.class));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("nome",result.getNome());
        Assertions.assertEquals(5,result.getQuantidade());
    }

    @Test
    void ingredienteGetAllTest() {
        List<IngredienteDTO> result = ingredientesService.getAll();
        Assertions.assertNotNull(result);
        assertThat(result).usingRecursiveComparison().isEqualTo(ingredienteDTOList);

    }

    @Test
    void ingredientePutTest(){
        Ingrediente ingredienteEntidade = new Ingrediente();

        IngredienteDTO result = ingredientesService.update(1L,ingredienteDTO);
        Assertions.assertNotNull(ingredientesService);
        assertThat(result).usingRecursiveComparison().isEqualTo(ingredienteDTO);
    }
    @Test
    void ingredienteDeletarTest() {
        ingredientesService.delete(1L);
        verify(ingredienteRepository, times(1)).deleteById(1L);
    }


}
