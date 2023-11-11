package com.example.uniamerica.pizzaria.ServiceTests;

import com.example.uniamerica.pizzaria.dto.ClienteDTO;
import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.entity.Endereco;
import com.example.uniamerica.pizzaria.entity.Role;
import com.example.uniamerica.pizzaria.repository.ClienteRepository;
import com.example.uniamerica.pizzaria.service.ClienteService;
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
 class ClienteServiceTests {
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private List<ClienteDTO> clienteDTOList = new ArrayList<>();
    private ClienteDTO clienteDTO = new ClienteDTO();
    private List<Endereco>enderecoList;
    private Cliente clienteEntidade = new Cliente();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        clienteEntidade.setId(1L);
        clienteEntidade.setNome("nome");
        clienteEntidade.setCpf("cpf");
        clienteEntidade.setTelefone("telefone");

        clienteDTO.setRole(Role.FUNCIONARIO);
        clienteDTO.setCpf("cpf");
        clienteDTO.setId(1L);
        clienteDTO.setNome("nome");
        clienteDTO.setTelefone("telefone");

        clienteDTOList.add(clienteDTO);


        when(repository.findAll()).thenReturn(clienteDTOList.stream().map(pessoaDTO -> service.toCliente(pessoaDTO)).toList());
        when(repository.save(Mockito.any(Cliente.class))).thenReturn(clienteEntidade);
        when(repository.findById(1L)).thenReturn(Optional.of(clienteEntidade));

    }
    @Test
    void pessoaDtoToPessoaEntityTest(){
        Cliente cliente = service.toCliente(clienteDTO);
        org.assertj.core.api.Assertions.assertThat(cliente).usingRecursiveComparison().isEqualTo(clienteEntidade);
    }
    @Test
    void pessoaEntidadeToPessoaDtoTest(){
        ClienteDTO pessoa = service.toClienteDTO(clienteEntidade);
        org.assertj.core.api.Assertions.assertThat(pessoa).usingRecursiveComparison().isEqualTo(clienteDTO);
    }

    @Test
    void pessoaGetById(){
        ClienteDTO result = service.findById(1L);
        Assertions.assertNotNull(result);
        verify(repository,times(1)).findById(1L);
    }

    @Test
    void pessoaGetAll(){
        List<ClienteDTO>result = service.getAll();
        Assertions.assertNotNull(service);
        assertThat(result).usingRecursiveComparison().isEqualTo(clienteDTOList);
    }

    @Test
    void pessoaPostTest(){
        ClienteDTO result = service.post(clienteDTO);
        verify(repository,times(1)).save(Mockito.any(Cliente.class));

        Assertions.assertNotNull(result);

        Assertions.assertEquals("nome",result.getNome());
        Assertions.assertEquals(1L,result.getId());
        Assertions.assertEquals("cpf",result.getCpf());
        Assertions.assertEquals("telefone",result.getTelefone());
        Assertions.assertEquals(Role.FUNCIONARIO,result.getRole());

    }

    @Test
    void pessoaPutTest(){
        ClienteDTO result = service.put(clienteDTO,1L);
        Assertions.assertNotNull(result);
        assertThat(result).usingRecursiveComparison().isEqualTo(clienteDTO);
    }

    @Test
    void pessoaDeletarTest() {
        service.desativar(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
