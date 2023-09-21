package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Entity.Endereco;
import com.example.uniamerica.pizzaria.Entity.Pessoa;
import com.example.uniamerica.pizzaria.Entity.TipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PessoaTests {
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
    @BeforeEach
    void setup() {
        pessoa.setId(1L);
        pessoa.setNome("Joao");
        pessoa.setCpf("676.990.230-30");
        pessoa.setTelefone("999999999");
        pessoa.setEndereco(endereco);
        pessoa.setTipoPessoa(TipoPessoa.FUNCIONARIO);
    }
    @Test
    void pessoaIdTest(){
        assertEquals(1L, pessoa.getId());
    }
    @Test
    void pessoaNomeTest(){
        assertEquals("Joao", pessoa.getNome());
    }
    @Test
    void pessoaCpfTest(){
        assertEquals("676.990.230-30", pessoa.getCpf());
    }
    @Test
    void pessoaTelefoneTest(){
        assertEquals("999999999", pessoa.getTelefone());
    }
    @Test
    void pessoaEnderecoTest(){
        assertEquals(endereco, pessoa.getEndereco());
    }
    @Test
    void pessoaTipoTest(){
        assertEquals(TipoPessoa.FUNCIONARIO, pessoa.getTipoPessoa());
    }
    @Test
    void pessoaAllArgsConstructorTest(){
        Pessoa pessoaAllArgs = new Pessoa("Joao", "676.990.230-30", "999999999", endereco, TipoPessoa.FUNCIONARIO);
        assertThat(pessoa).usingRecursiveComparison().ignoringFields("id").isEqualTo(pessoaAllArgs);
    }
}
