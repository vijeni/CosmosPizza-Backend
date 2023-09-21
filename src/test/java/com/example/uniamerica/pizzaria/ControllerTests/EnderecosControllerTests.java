package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.EnderecoController;
import com.example.uniamerica.pizzaria.dto.EnderecoDTO;
import com.example.uniamerica.pizzaria.entity.Endereco;
import com.example.uniamerica.pizzaria.repository.EnderecoRepository;
import com.example.uniamerica.pizzaria.service.EnderecosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnderecosControllerTests {

    @Autowired
    private EnderecoController enderecoController;

    @Autowired
    private EnderecosService enderecosService; //Nota mental: O @InjectMocks não funciona em Service.

    @MockBean
    private EnderecoRepository repository;

    private List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
    private List<Endereco>enderecoList = new ArrayList<>();
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
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(enderecoEntidade));
        when(repository.findAll()).thenReturn(enderecoList);


    }

    @Test
    void enderecoFindById(){
       ResponseEntity<EnderecoDTO> result = enderecoController.findById(1L);
       assertEquals(HttpStatus.OK,result.getStatusCode());
       assertThat(result.getBody()).usingRecursiveComparison().isEqualTo(enderecoDTO);
    }
    /*
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
        assertEquals("bairro",result.getBairro());
        assertEquals("cep",result.getCep());
        assertEquals(1L,result.getId());
        assertEquals(579,result.getNumero());
        assertEquals("logradouro",result.getLogradouro());
        assertEquals("complemento",result.getComplemento());
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

     */
}
