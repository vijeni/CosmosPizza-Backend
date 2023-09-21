package com.example.uniamerica.pizzaria.EntityTests;

import com.example.uniamerica.pizzaria.entity.Pedido;
import com.example.uniamerica.pizzaria.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class ProdutoTests {
    private Produto produto = new Produto();
    private List<Pedido> pedidos = new ArrayList<>();

    @BeforeEach
    void setup() {
        pedidos.add(new Pedido());
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setQuantidadeEstoque(10);
        produto.setDescricao("Descricao");
        produto.setValorUnitario(10D);
        produto.setPedidos(pedidos);
    }

    @Test
    void produtoIdTest(){
        assertEquals(1L, produto.getId());
    }
    @Test
    void produtoNomeTest(){
        assertEquals("Produto A", produto.getNome());
    }
    @Test
    void produtoQuantidadeEstoqueTest(){
        assertEquals(10, produto.getQuantidadeEstoque());
    }
    @Test
    void produtoDescricaoTest(){
        assertEquals("Descricao", produto.getDescricao());
    }
    @Test
    void produtoValorUnitarioTest(){
        assertEquals(10D, produto.getValorUnitario());
    }

    @Test
    void produtoPedidosTest(){
        assertEquals(pedidos, produto.getPedidos());
    }
    @Test
    void produtoAllArgsConstructorTest(){
        Produto produtoAllArgs = new Produto("Produto A", 10D,  10, "Descricao", pedidos);
        assertThat(produto).usingRecursiveComparison().ignoringFields("id").isEqualTo(produtoAllArgs);
    }

}
