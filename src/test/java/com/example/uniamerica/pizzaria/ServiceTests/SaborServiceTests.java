package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Repository.SaborRepository;
import com.example.uniamerica.pizzaria.Service.SaborService;
import org.hibernate.event.spi.SaveOrUpdateEvent;
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
public class SaborServiceTests {

    @Mock
    private SaborRepository repository;

    @InjectMocks
    private SaborService service;

    private SaborDTO saborDTO = new SaborDTO();
    private List<SaborDTO>saborDTOList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        saborDTO.setNome("nome");
        saborDTO.setId(1L);
        saborDTO.setDescricao("descrição");

        Sabor saborEntidade = new Sabor();
        saborEntidade.setNome("nome");
        saborEntidade.setId(1L);
        saborEntidade.setDescricao("descrição");

        saborDTOList.add(saborDTO);

        when(repository.findAll()).thenReturn(saborDTOList.stream().map(saborDTO -> service.toSabor(saborDTO)).toList());
        when(repository.save(Mockito.any(Sabor.class))).thenReturn(saborEntidade);
        when(repository.findById(1L)).thenReturn(Optional.of(saborEntidade));

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


}
