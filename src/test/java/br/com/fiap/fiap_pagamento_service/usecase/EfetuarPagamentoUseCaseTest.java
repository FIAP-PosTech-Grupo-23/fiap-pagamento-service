package br.com.fiap.fiap_pagamento_service.usecase;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.gateway.PagamentoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.SistemaPagamentoExternoGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EfetuarPagamentoUseCaseTest {

    AutoCloseable mock;

    @Mock
    private SistemaPagamentoExternoGateway sistemaPagamentoExternoGateway;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private EfetuarPagamentoUseCase efetuarPagamentoUseCase;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCallBothGatewaysAndReturnId() {

        // Arrange
        Pagamento pagamento = new Pagamento(BigDecimal.valueOf(5000L),
                "5189 7498 3445 2546",
                "123",
                "03/2027",
                "Guilherme Lima",
                "786.897.260-20",
                "Rua Fiap, n. 123");
        doReturn(UUID.randomUUID()).when(sistemaPagamentoExternoGateway).solicitarPagamento(any(Pagamento.class));
        doReturn(1L).when(pagamentoGateway).salvar(any(Pagamento.class));

        // Act
        var res = efetuarPagamentoUseCase.efetuar(pagamento);

        // Assert
        verify(sistemaPagamentoExternoGateway).solicitarPagamento(pagamento);
        verify(pagamentoGateway).salvar(pagamento);
        assertEquals(1L, res);
    }

}