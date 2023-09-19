package com.example.uniamerica.pizzaria.ControllerTests;

import com.example.uniamerica.pizzaria.Controller.PedidoController;
import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.Service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PedidoControllerTeste {
    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest() {
        Long idPedido = 1L;
        PedidoDTO pedidosDTO = new PedidoDTO();
        pedidosDTO.setId(idPedido);
        when(pedidoService.findById(idPedido)).thenReturn(pedidosDTO);
        ResponseEntity<PedidoDTO> response = pedidoController.findById(idPedido);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidosDTO, response.getBody());
    }
}

