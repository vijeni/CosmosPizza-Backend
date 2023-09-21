package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.dto.PizzaDTO;
import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.entity.Pizza;
import com.example.uniamerica.pizzaria.entity.Sabor;
import com.example.uniamerica.pizzaria.entity.Tamanho;
import com.example.uniamerica.pizzaria.repository.PizzaRepository;
import com.example.uniamerica.pizzaria.service.PizzaService;
import com.example.uniamerica.pizzaria.service.SaborService;
import com.example.uniamerica.pizzaria.service.TamanhoService;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
 class PizzaServiceTests {

    @Mock
    private TamanhoService tamanhoService;
    @Mock
    private SaborService saborService;
    @Mock
    PizzaRepository repository;
    @InjectMocks
    private PizzaService service;

    PizzaDTO pizzaDTO = new PizzaDTO();
    Pizza pizzaEntity = new Pizza();
    TamanhoDTO tamanhoDTO = new TamanhoDTO();
    Tamanho tamanho = new Tamanho();
    List<SaborDTO> saborDTOS = new ArrayList<>();
    List<Sabor> sabores = new ArrayList<>();
    SaborDTO saborDTO = new SaborDTO();
    Sabor sabor = new Sabor();

    List<PizzaDTO> pizzaDTOList = new ArrayList<>();
    List<Pizza> pizzaEntityList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        tamanhoDTO.setId(1L);
        tamanhoDTO.setNome("Média");
        tamanhoDTO.setValor(40D);
        tamanhoDTO.setMaximoSabores(3);

        tamanho.setId(1L);
        tamanho.setNome("Média");
        tamanho.setValor(40D);
        tamanho.setMaximoSabores(3);

        saborDTO.setId(1L);
        saborDTO.setNome("Calabresa");
        saborDTOS.add(saborDTO);
        saborDTOS.add(saborDTO);

        sabor.setId(1L);
        sabor.setNome("Calabresa");
        sabores.add(sabor);
        sabores.add(sabor);

        pizzaDTO.setId(1L);
        pizzaDTO.setTamanho(tamanhoDTO);
        pizzaDTO.setSabores(saborDTOS);
        pizzaDTOList.add(pizzaDTO);

        pizzaEntity.setId(1L);
        pizzaEntity.setTamanho(tamanho);
        pizzaEntity.setSabores(sabores);
        pizzaEntityList.add(pizzaEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(pizzaEntity));
        when(repository.findAll()).thenReturn(pizzaEntityList);
        when(repository.save(Mockito.any(Pizza.class))).thenReturn(pizzaEntity);
        when(saborService.findById(1L)).thenReturn(saborDTO);
        when(tamanhoService.findById(1L)).thenReturn(tamanhoDTO);

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
    void pizzaGetAllTest(){
        List<PizzaDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(pizzaDTOList);
    }

    @Test
    void pizzaCadastrarTest(){
        PizzaDTO retornoService = service.cadastrar(pizzaDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(pizzaDTO);
    }
    @Test
    void pizzaEditarTest(){
        PizzaDTO retornoService = service.editar(1L, pizzaDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(pizzaDTO);
    }

    @Test
    void validarPizzasTest(){
        service.validarPizzas(pizzaDTOList);
        verify(tamanhoService, times(1)).findById(1L);
        verify(saborService, times(2)).findById(1L);
    }
    @Test
    void validarPizzasExceptionTest(){
        tamanhoDTO.setMaximoSabores(1);
        assertThrows(IllegalArgumentException.class, () -> {
            service.validarPizzas(pizzaDTOList);
        });
    }
    @Test
    void pizzaValorTest(){
        Double valor = service.valorPizzas(pizzaDTOList);
        assertEquals(40D, valor);
    }
    @Test
    void pizzaDeletarTest() {
        service.deletar(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
