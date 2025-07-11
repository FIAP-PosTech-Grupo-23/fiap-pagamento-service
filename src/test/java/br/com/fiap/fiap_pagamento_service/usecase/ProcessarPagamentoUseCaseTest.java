package br.com.fiap.fiap_pagamento_service.usecase;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.domain.StatusPagamento;
import br.com.fiap.fiap_pagamento_service.gateway.PagamentoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.PedidoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.SistemaPagamentoExternoGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProcessarPagamentoUseCaseTest {

    AutoCloseable mock;

    @Mock
    private SistemaPagamentoExternoGateway sistemaPagamentoExternoGateway;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCallAllGateways() {

        // Arrange
        UUID uuid = UUID.randomUUID();

        Pagamento pagamento = new Pagamento(1L,
                BigDecimal.valueOf(5000L),
                "5189 7498 3445 2546",
                "123",
                "03/2027",
                "Guilherme Lima",
                "786.897.260-20",
                "Rua Fiap, n. 123",
                uuid);

        doReturn(pagamento).when(pagamentoGateway).buscaPorIdSistemaExterno(any(UUID.class));
        doReturn(StatusPagamento.APROVADO).when(sistemaPagamentoExternoGateway).obterDados(any(UUID.class));
        doNothing().when(pedidoGateway).notificaPagamento(anyLong(), anyString());

        // Act
        processarPagamentoUseCase.processar(uuid);

        // Assert
        verify(pagamentoGateway).buscaPorIdSistemaExterno(uuid);
        verify(sistemaPagamentoExternoGateway).obterDados(uuid);
        verify(pedidoGateway).notificaPagamento(1L, StatusPagamento.APROVADO.name());
    }

}