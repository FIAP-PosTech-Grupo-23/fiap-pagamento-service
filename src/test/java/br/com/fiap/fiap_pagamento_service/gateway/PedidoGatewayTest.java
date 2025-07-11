package br.com.fiap.fiap_pagamento_service.gateway;

import br.com.fiap.fiap_pagamento_service.gateway.pedido.PedidoGatewayImpl;
import br.com.fiap.fiap_pagamento_service.gateway.pedido.client.PedidoClient;
import br.com.fiap.fiap_pagamento_service.gateway.pedido.json.NotificaPagamentoJson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class PedidoGatewayTest {

    AutoCloseable mock;

    @Mock
    private PedidoClient pedidoClient;

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCallPedidoClientWithJson() {

        // Arrange
        Long idPagamento = 1L;
        String statusPagamento = "APROVADO";
        doNothing().when(pedidoClient).notificaPagamento(any(NotificaPagamentoJson.class));

        // Act
        pedidoGateway.notificaPagamento(idPagamento, statusPagamento);

        // Assert
        ArgumentCaptor<NotificaPagamentoJson> captor = ArgumentCaptor.forClass(NotificaPagamentoJson.class);
        verify(pedidoClient).notificaPagamento(captor.capture());
        NotificaPagamentoJson jsonCaptured = captor.getValue();
        assertEquals(idPagamento, jsonCaptured.idPagamento());
        assertEquals(statusPagamento, jsonCaptured.statusPagamento());
    }

}