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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
 class IngredienteServiceTests {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredientesService ingredientesService;


    private IngredienteDTO ingredienteDTO;
    private Sabor sabor;
    private List<IngredienteDTO> ingredienteDTOList = new ArrayList<IngredienteDTO>();


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setId(1L);
        ingredienteDTO.setNome("nome");
        ingredienteDTO.setQuantidade(5);


        Ingrediente ingredienteEntidade = new Ingrediente();
        ingredienteEntidade.setId(1L);
        ingredienteEntidade.setNome("nome");
        ingredienteEntidade.setQuantidade(5);

        ingredienteDTOList.add(ingredienteDTO);

        when(ingredienteRepository.findAll()).thenReturn(ingredienteDTOList.stream().map(ingredienteDTO -> ingredientesService.toIngredienteEntidade(ingredienteDTO)).toList());
        when(ingredienteRepository.save(Mockito.any(Ingrediente.class))).thenReturn(ingredienteEntidade);
        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(ingredienteEntidade));

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
        /*
        List<Ingrediente> ingredienteList = new ArrayList<>();
        List<Sabor> saboresSimulados = new ArrayList<>();
        saboresSimulados.add(new Sabor("Sabor 1", "Descrição 1", null, null));
        saboresSimulados.add(new Sabor("Sabor 2", "Descrição 2", null, null));
        ingredienteList.add(new Ingrediente("Nome 01", 2, saboresSimulados));
        ingredienteList.add(new Ingrediente("Nome 02", 2, saboresSimulados));

        when(ingredienteRepository.findAll()).thenReturn(ingredienteList);

        List<IngredienteDTO> resultado = ingredientesService.getAll();

        // Converter a lista de entidades em uma lista de DTOs para comparação
        List<IngredienteDTO> ingredienteDTOList = ingredienteList.stream()
                .map(ingredientesService::toIngredienteDTO) // Usar seu método de conversão
                .collect(Collectors.toList());

        assertThat(resultado).isEqualTo(ingredienteDTOList);

         */
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


}
