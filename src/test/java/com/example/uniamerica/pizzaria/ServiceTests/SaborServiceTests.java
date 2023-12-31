package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.dto.IngredienteDTO;
import com.example.uniamerica.pizzaria.dto.PizzaDTO;
import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.entity.Ingrediente;
import com.example.uniamerica.pizzaria.entity.Pizza;
import com.example.uniamerica.pizzaria.entity.Sabor;
import com.example.uniamerica.pizzaria.repository.SaborRepository;
import com.example.uniamerica.pizzaria.service.SaborService;
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
 class SaborServiceTests {

    @Mock
    private SaborRepository repository;

    @InjectMocks
    private SaborService service;

    private SaborDTO saborDTO;
    private List<SaborDTO>saborDTOList = new ArrayList<>();
    private List<PizzaDTO>pizzaDTOList = new ArrayList<>();

    private List<IngredienteDTO>ingredienteDTOList = new ArrayList<>();
    private List<Ingrediente>ingredienteList = new ArrayList<>();
    private List<Pizza>pizzaList = new ArrayList<>();
    private Sabor saborEntidade = new Sabor();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        saborDTO = new SaborDTO();

        saborDTO.setNome("nome");
        saborDTO.setId(1L);
        saborDTO.setDescricao("descrição");

        ingredienteDTOList.add(new IngredienteDTO());
        pizzaDTOList.add(new PizzaDTO());

        saborDTO.setPizzas(pizzaDTOList);
        saborDTO.setIngredientes(ingredienteDTOList);

        saborEntidade.setNome("nome");
        saborEntidade.setId(1L);
        saborEntidade.setDescricao("descrição");

        ingredienteList.add(new Ingrediente());
        pizzaList.add(new Pizza());

        saborEntidade.setIngredientes(ingredienteList);
        saborEntidade.setPizzas(pizzaList);


        saborDTOList.add(saborDTO);

        when(repository.findAll()).thenReturn(saborDTOList.stream().map(saborDTO -> service.toSabor(saborDTO)).toList());
        when(repository.save(Mockito.any(Sabor.class))).thenReturn(saborEntidade);
        when(repository.findById(1L)).thenReturn(Optional.of(saborEntidade));

    }
    @Test
    void saborDtoToSaborEntityTest(){
        Sabor sabor = service.toSabor(saborDTO);
        org.assertj.core.api.Assertions.assertThat(sabor).usingRecursiveComparison().isEqualTo(saborEntidade);
    }

    @Test
    void saborEntidadeToSaborDtoTest(){
        SaborDTO sabor = service.toSaborDTO(saborEntidade);
        org.assertj.core.api.Assertions.assertThat(sabor).usingRecursiveComparison().isEqualTo(saborDTO);
    }


    @Test
    void saborGetByIdTest(){
        SaborDTO result = service.findById(1L);
        Assertions.assertNotNull(result);
        verify(repository,times(1)).findById(1L);
    }

    @Test
    void saborGetAllTest(){
        List<SaborDTO>result = service.getAll();
        Assertions.assertNotNull(result);
        assertThat(result).usingRecursiveComparison().isEqualTo(saborDTOList);
    }

    @Test
    void saborPostTest(){
        SaborDTO result = service.cadastrar(saborDTO);
        Assertions.assertNotNull(result);
        verify(repository,times(1)).save(Mockito.any(Sabor.class));

        Assertions.assertEquals("nome",result.getNome());
        Assertions.assertEquals(1L,result.getId());
        Assertions.assertEquals("descrição",result.getDescricao());
    }

    @Test
    void saborPutTest(){
        SaborDTO result = service.update(saborDTO,1L);
        Assertions.assertNotNull(service);
        assertThat(result).usingRecursiveComparison().isEqualTo(saborDTO);
    }

    @Test
    void saborDeletarTest() {
        service.desativar(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
