package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.dto.EnderecoDTO;
import com.example.uniamerica.pizzaria.dto.ClienteDTO;
import com.example.uniamerica.pizzaria.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class ClienteDTOTests {
    private ClienteDTO clienteDTO = new ClienteDTO();
    private EnderecoDTO endereco = new EnderecoDTO();
    @BeforeEach
    void setup() {
        clienteDTO.setId(1L);
        clienteDTO.setNome("Joao");
        clienteDTO.setCpf("676.990.230-30");
        clienteDTO.setTelefone("999999999");
        clienteDTO.setEndereco(endereco);
        clienteDTO.setRole(Role.FUNCIONARIO);
    }
    @Test
    void pessoaIdTest(){
        assertEquals(1L, clienteDTO.getId());
    }
    @Test
    void pessoaNomeTest(){
        assertEquals("Joao", clienteDTO.getNome());
    }
    @Test
    void pessoaCpfTest(){
        assertEquals("676.990.230-30", clienteDTO.getCpf());
    }
    @Test
    void pessoaTelefoneTest(){
        assertEquals("999999999", clienteDTO.getTelefone());
    }
    @Test
    void pessoaEnderecoTest(){
        assertEquals(endereco, clienteDTO.getEndereco());
    }
    @Test
    void pessoaTipoTest(){
        assertEquals(Role.FUNCIONARIO, clienteDTO.getRole());
    }
    @Test
    void pessoaAllArgsConstructorTest(){
        ClienteDTO pessoaAllArgs = new ClienteDTO("Joao", "676.990.230-30", "999999999", endereco, Role.FUNCIONARIO);
        assertThat(clienteDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(pessoaAllArgs);
    }
}
