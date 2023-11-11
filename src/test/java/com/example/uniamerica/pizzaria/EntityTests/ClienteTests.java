package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.entity.Endereco;
import com.example.uniamerica.pizzaria.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class ClienteTests {
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();
    @BeforeEach
    void setup() {
        cliente.setId(1L);
        cliente.setNome("Joao");
        cliente.setCpf("676.990.230-30");
        cliente.setTelefone("999999999");
        cliente.setEndereco(endereco);
        cliente.setTipoPessoa(Role.FUNCIONARIO);
    }
    @Test
    void pessoaIdTest(){
        assertEquals(1L, cliente.getId());
    }
    @Test
    void pessoaNomeTest(){
        assertEquals("Joao", cliente.getNome());
    }
    @Test
    void pessoaCpfTest(){
        assertEquals("676.990.230-30", cliente.getCpf());
    }
    @Test
    void pessoaTelefoneTest(){
        assertEquals("999999999", cliente.getTelefone());
    }
    @Test
    void pessoaEnderecoTest(){
        assertEquals(endereco, cliente.getEndereco());
    }
    @Test
    void pessoaTipoTest(){
        assertEquals(Role.FUNCIONARIO, cliente.getTipoPessoa());
    }
    @Test
    void pessoaAllArgsConstructorTest(){
        Cliente clienteAllArgs = new Cliente("Joao", "676.990.230-30", "999999999", endereco, Role.FUNCIONARIO);
        assertThat(cliente).usingRecursiveComparison().ignoringFields("id").isEqualTo(clienteAllArgs);
    }
}
