package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.controller.EnderecoController;
import com.example.uniamerica.pizzaria.dto.EnderecoDTO;
import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
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
import static org.assertj.core.api.AssertionsForClassTypes.contentOf;
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
    EnderecoDTO enderecoDTO = new EnderecoDTO();
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

        enderecoDTO.setBairro("bairro");
        enderecoDTO.setCep("cep");
        enderecoDTO.setId(1L);
        enderecoDTO.setNumero(579);
        enderecoDTO.setLogradouro("logradouro");
        enderecoDTO.setComplemento("complemento");

        enderecoDTOList.add(enderecoDTO);
        enderecoList.add(enderecoEntidade);

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

    @Test
    void enderecoListAll(){
        ResponseEntity<List<EnderecoDTO>>response = enderecoController.findAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(enderecoDTOList);
    }

    @Test
    void enderecoPost(){
        ResponseEntity<EnderecoDTO>response = enderecoController.post(enderecoDTO);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(enderecoDTO);
    }

    @Test
    void enderecoPut(){
       ResponseEntity<EnderecoDTO>response = enderecoController.put(1L,enderecoDTO);
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(enderecoDTO);

    }

    @Test
    void enderecoDeletarTest() {
        ResponseEntity<String>response = enderecoController.delete(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(repository, times(1)).deleteById(1L);
    }


}
