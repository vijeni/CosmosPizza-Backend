package com.example.uniamerica.pizzaria.ServiceTests;
import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.entity.Tamanho;
import com.example.uniamerica.pizzaria.repository.TamanhoRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
 class TamanhoServiceTests {
    @Mock
    TamanhoRepository repository;
    @InjectMocks
    private TamanhoService service;

    TamanhoDTO tamanhoDTO = new TamanhoDTO();
    Tamanho tamanhoEntity = new Tamanho();
    List<TamanhoDTO> tamanhosDTOList = new ArrayList<>();
    List<Tamanho> tamanhosEntityList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        tamanhoDTO.setId(1L);
        tamanhoDTO.setNome("GRANDE");
        tamanhoDTO.setValor(80D);
        tamanhoDTO.setMaximoSabores(4);
        tamanhosDTOList.add(tamanhoDTO);

        tamanhoEntity.setId(1L);
        tamanhoEntity.setNome("GRANDE");
        tamanhoEntity.setValor(80D);
        tamanhoEntity.setMaximoSabores(4);
        tamanhosEntityList.add(tamanhoEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(tamanhoEntity));
        when(repository.findAll()).thenReturn(tamanhosEntityList);
        when(repository.findByNameLike(Mockito.any(String.class))).thenReturn(tamanhosEntityList);
        when(repository.save(Mockito.any(Tamanho.class))).thenReturn(tamanhoEntity);
    }
    @Test
    void tamanhoDtoToTamanhoEntityTest(){
        Tamanho tamanho = service.toTamanho(tamanhoDTO);
        assertThat(tamanho).usingRecursiveComparison().isEqualTo(tamanhoEntity);
    }

    @Test
    void tamanhoToTamanhoDTOTest(){
        TamanhoDTO tamanho = service.toTamanhoDTO(tamanhoEntity);
        assertThat(tamanho).usingRecursiveComparison().isEqualTo(tamanhoDTO);
    }
    @Test
    void tamanhoFindByIdTest(){
        TamanhoDTO retornoService = service.findById(1L);
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tamanhoDTO);
    }
    @Test
    void tamanhoGetAllTest(){
        List<TamanhoDTO> retornoService = service.getAll();
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tamanhosDTOList);
    }

    @Test
    void tamanhoGetAllByNomeTest(){
        List<TamanhoDTO> retornoService = service.getAllByNome("GRANDE");
        assertNotNull(retornoService);
        assertThat(retornoService)
                .usingRecursiveComparison()
                .isEqualTo(tamanhosDTOList);
    }

    @Test
    void tamanhoCadastrarTest(){
        TamanhoDTO tamanhoCadastrar = new TamanhoDTO();
        tamanhoCadastrar.setNome("GRANDE");
        tamanhoCadastrar.setValor(80D);
        tamanhoCadastrar.setMaximoSabores(4);
        TamanhoDTO retornoService = service.cadastrar(tamanhoCadastrar);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(tamanhoDTO);
    }
    @Test
    void tamanhoEditarTest(){
        TamanhoDTO retornoService = service.editar(1L, tamanhoDTO);
        assertNotNull(retornoService);
        assertThat(retornoService).usingRecursiveComparison().isEqualTo(tamanhoDTO);
    }

    @Test
    void tamanhoDeletarTest() {
        service.desativar(1L);
        Mockito.verify(repository, times(1)).deleteById(1L);
    }
}
