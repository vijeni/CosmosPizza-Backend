package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Entity.Endereco;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.EnderecoRepository;
import com.example.uniamerica.pizzaria.Service.EnderecosService;
import org.junit.Before;
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
    class EnderecoServiceTests {

    @Mock
    private EnderecoRepository repository;

    @InjectMocks
    private EnderecosService service;

    private List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
    private EnderecoDTO enderecoDTO;
    private Endereco enderecoEntidade = new Endereco();



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        enderecoEntidade.setBairro("bairro");
        enderecoEntidade.setCep("cep");
        enderecoEntidade.setId(1L);
        enderecoEntidade.setNumero(579);
        enderecoEntidade.setLogradouro("logradouro");
        enderecoEntidade.setComplemento("complemento");

        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro("bairro");
        enderecoDTO.setCep("cep");
        enderecoDTO.setId(1L);
        enderecoDTO.setNumero(579);
        enderecoDTO.setLogradouro("logradouro");
        enderecoDTO.setComplemento("complemento");

        enderecoDTOList.add(enderecoDTO);

        when(repository.save(Mockito.any(Endereco.class))).thenReturn(enderecoEntidade);
        when(repository.findById(1L)).thenReturn(Optional.of(enderecoEntidade));
        when(repository.findAll()).thenReturn(enderecoDTOList.stream().map(enderecoDTO -> service.toEnderecos(enderecoDTO)).toList());


    }
    @Test
    void enderecoDtoToEnderecoEntityTest(){
        Endereco endereco = service.toEnderecos(enderecoDTO);
        org.assertj.core.api.Assertions.assertThat(endereco).usingRecursiveComparison().isEqualTo(enderecoEntidade);
    }
    @Test
    void enderecoEntityToEnderecoDtoTest(){
        EnderecoDTO endereco = service.toEnderecosDTO(enderecoEntidade);
        org.assertj.core.api.Assertions.assertThat(endereco).usingRecursiveComparison().isEqualTo(enderecoDTO);
    }
    @Test
    void enderecoFindById(){
        EnderecoDTO result = service.findById(1L);

        Assertions.assertNotNull(result);

        Mockito.verify(repository, Mockito.times(1)).findById(1L);

    }

    @Test
    void enderecoListAll(){
        List<EnderecoDTO> result = service.listAll();
        Assertions.assertNotNull(result);
        assertThat(result).usingRecursiveComparison().isEqualTo(enderecoDTOList);
    }

    @Test
    void enderecoPost(){
        EnderecoDTO result = service.post(enderecoDTO);
        verify(repository,times(1)).save(Mockito.any(Endereco.class));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("bairro",result.getBairro());
        Assertions.assertEquals("cep",result.getCep());
        Assertions.assertEquals(1L,result.getId());
        Assertions.assertEquals(579,result.getNumero());
        Assertions.assertEquals("logradouro",result.getLogradouro());
        Assertions.assertEquals("complemento",result.getComplemento());
    }

    @Test
    void enderecoPut(){
        EnderecoDTO result = service.update(enderecoDTO,1L);
        Assertions.assertNotNull(service);
        assertThat(result).usingRecursiveComparison().isEqualTo(enderecoDTO);
    }

    @Test
    void enderecoDeletarTest() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

}
