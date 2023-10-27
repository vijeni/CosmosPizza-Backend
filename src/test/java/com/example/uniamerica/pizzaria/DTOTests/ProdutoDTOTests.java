package com.example.uniamerica.pizzaria.DTOTests;

import com.example.uniamerica.pizzaria.dto.PedidoDTO;
import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class ProdutoDTOTests {
    private ProdutoDTO produtoDTO = new ProdutoDTO();
    private List<PedidoDTO> pedidos = new ArrayList<>();

    @BeforeEach
    void setup() {
        pedidos.add(new PedidoDTO());
        produtoDTO.setId(1L);
        produtoDTO.setDescricao("Produto A");
        produtoDTO.setQuantidadeEstoque(10);
        produtoDTO.setObservacao("Descricao");
        produtoDTO.setValorUnitario(10D);
        produtoDTO.setPedidos(pedidos);
    }

    @Test
    void produtoIdTest(){
        assertEquals(1L, produtoDTO.getId());
    }
    @Test
    void produtoNomeTest(){
        assertEquals("Produto A", produtoDTO.getDescricao());
    }
    @Test
    void produtoQuantidadeEstoqueTest(){
        assertEquals(10, produtoDTO.getQuantidadeEstoque());
    }
    @Test
    void produtoDescricaoTest(){
        assertEquals("Descricao", produtoDTO.getObservacao());
    }
    @Test
    void produtoValorUnitarioTest(){
        assertEquals(10D, produtoDTO.getValorUnitario());
    }

    @Test
    void produtoPedidosTest(){
        assertEquals(pedidos, produtoDTO.getPedidos());
    }
    @Test
    void produtoAllArgsConstructorTest(){
        ProdutoDTO produtoAllArgs = new ProdutoDTO("Produto A", 10, "Descricao", 10D, pedidos);
        assertThat(produtoDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(produtoAllArgs);
    }

}
