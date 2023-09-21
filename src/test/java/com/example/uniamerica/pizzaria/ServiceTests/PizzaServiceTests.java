package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Pizza;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.PizzaRepository;
import com.example.uniamerica.pizzaria.Service.PizzaService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PizzaServiceTests {
    @Mock
    PizzaRepository repository;
    @InjectMocks
    private PizzaService service;

    PizzaDTO pizzaDTO = new PizzaDTO();
    Pizza pizzaEntity = new Pizza();
    List<PizzaDTO> pizzaDTOList = new ArrayList<>();
    List<Pizza> pizzaEntityList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        pizzaDTO.setId(1L);
        pizzaDTOList.add(pizzaDTO);

        pizzaEntity.setId(1L);
        pizzaEntityList.add(pizzaEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(pizzaEntity));
        when(repository.findAll()).thenReturn(pizzaEntityList);
        when(repository.save(Mockito.any(Pizza.class))).thenReturn(pizzaEntity);
    }
    @Test
    void pizzaDtoToPizzaEntityTest(){
        Pizza pizza = service.toPizza(pizzaDTO);
        assertThat(pizza).usingRecursiveComparison().isEqualTo(pizzaEntity);
    }

    @Test
    void pizzaEntityToPizzaDTOTest(){
        PizzaDTO pizzaDTO = service.toPizzaDTO(pizzaEntity);
        assertThat(pizzaDTO).usingRecursiveComparison().isEqualTo(this.pizzaDTO);
    }
    @Test
    void pizzaFindByIdTest(){
        PizzaDTO retornoService = service.findById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(pizzaDTO);
    }

    @Test
    void tamanhoGetAllTest(){
        List<PizzaDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(pizzaDTOList);
    }

    @Test
    void tamanhoCadastrarTest(){
        PizzaDTO retornoService = service.cadastrar(pizzaDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(pizzaDTO);
    }
    @Test
    void tamanhoEditarTest(){
        PizzaDTO retornoService = service.editar(1L, pizzaDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(pizzaDTO);
    }
}
