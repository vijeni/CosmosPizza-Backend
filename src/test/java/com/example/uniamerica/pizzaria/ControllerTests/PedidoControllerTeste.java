package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.PedidoController;
import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Entity.Status;
import com.example.uniamerica.pizzaria.Service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PedidoControllerTeste {
    @InjectMocks
    private PedidoController controller;

    @Mock
    private PedidoService service;

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest() {
        Long idPedido = 1L;
        PedidoDTO pedidosDTO = new PedidoDTO();
        pedidosDTO.setId(idPedido);
        when(service.findById(idPedido)).thenReturn(pedidosDTO);

        ResponseEntity<PedidoDTO> controllerResponse = controller.findById(idPedido);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(pedidosDTO, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void getAllTest(){
        PedidoDTO pedido = new PedidoDTO();
        pedido.setId(1L);
        pedido.setCliente(new PessoaDTO());
        pedido.setFuncionario(new PessoaDTO());
        List<PedidoDTO> pedidosDTOList = new ArrayList<>();
        pedidosDTOList.add(pedido);
        when(service.getAll()).thenReturn(pedidosDTOList);
        ResponseEntity<List<PedidoDTO>> controllerResponse = controller.getAll();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(pedidosDTOList, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void finalizarPedidoTest() {
        Long idPedido = 1L;
        PedidoDTO pedido = new PedidoDTO();
        pedido.setId(idPedido);

        when(service.finalizarPedido(1L)).thenReturn(pedido);
        ResponseEntity<PedidoDTO> controllerResponse = controller.finalizarPedido(1L);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(pedido, controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }
    @Test
    void cadastrarPedidoTest() {
        PedidoDTO pedido = new PedidoDTO();
        when(service.cadastrar(pedido)).thenReturn(pedido);
        ResponseEntity<PedidoDTO> controllerResponse = controller.cadastrar(pedido);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(pedido, controllerResponse.getBody());
    }
    @Test
    void editarPedidoTest() {
        Long idPedido = 1L;
        PedidoDTO pedido = new PedidoDTO();
        pedido.setId(idPedido);
        when(service.editar(idPedido, pedido)).thenReturn(pedido);
        ResponseEntity<PedidoDTO> controllerResponse = controller.editar(idPedido, pedido);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals(pedido, controllerResponse.getBody());
    }
    @Test
    void deletarPedidoTest() {
        Long idPedido = 1L;
        ResponseEntity<String> controllerResponse = controller.deletar(idPedido);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertEquals("Pedido com ID 1 foi deletado com sucesso!", controllerResponse.getBody());
        System.out.println(controllerResponse.getBody());
    }

}

