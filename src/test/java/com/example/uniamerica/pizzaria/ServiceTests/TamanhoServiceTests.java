package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.TamanhoRepository;
import com.example.uniamerica.pizzaria.Service.TamanhoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TamanhoServiceTests {
    @Mock
    TamanhoRepository repository;
    @InjectMocks
    private TamanhoService service;

    TamanhoDTO tamanho = new TamanhoDTO();
    List<TamanhoDTO> tamanhosDTOList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        tamanho.setId(1L);
        tamanho.setTamanho("GRANDE");
        tamanho.setValor(80D);
        tamanho.setMaximoSabores(4);
        tamanhosDTOList.add(tamanho);

        when(repository.findById(1L)).thenReturn(Optional.of(service.toTamanho(tamanho)));
        when(repository.findAll()).thenReturn(tamanhosDTOList.stream().map(tamanhoDTO -> service.toTamanho(tamanhoDTO)).toList());
    }
    @Test
    void findByIdTest(){
        TamanhoDTO retornoService = service.findById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tamanho);
    }

    @Test
    void getAllTest(){
        List<TamanhoDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tamanhosDTOList);
    }
}
