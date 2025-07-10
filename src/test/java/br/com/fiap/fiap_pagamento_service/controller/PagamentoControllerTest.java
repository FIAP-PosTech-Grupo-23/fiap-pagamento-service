package br.com.fiap.fiap_pagamento_service.controller;

import br.com.fiap.fiap_pagamento_service.controller.json.PagamentoDetailsJson;
import br.com.fiap.fiap_pagamento_service.controller.json.PagamentoJson;
import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.usecase.EfetuarPagamentoUseCase;
import br.com.fiap.fiap_pagamento_service.usecase.ProcessarPagamentoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {

    AutoCloseable mock;

    @Mock
    private EfetuarPagamentoUseCase efetuarPagamentoUseCase;

    @Mock
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCreatePagamentoAndReturnId() {

        // Arrange
        PagamentoJson pagamentoJson = new PagamentoJson(BigDecimal.valueOf(1000L),
                new PagamentoDetailsJson("5189 7498 3445 2546", "434", "03/2026"),
                "Luis",
                "786.897.260-20",
                "Rua Fiap");
        doReturn(1L).when(efetuarPagamentoUseCase).efetuar(any(Pagamento.class));

        // Act
        var res = pagamentoController.novoPagamento(pagamentoJson);

        // Assert
        ArgumentCaptor<Pagamento> captor = ArgumentCaptor.forClass(Pagamento.class);
        verify(efetuarPagamentoUseCase).efetuar(captor.capture());
        Pagamento pagamento = captor.getValue();
        assertEquals("5189 7498 3445 2546", pagamento.getNumeroCartao());
        assertNotNull(res.getBody());

    }

    @Test
    void shouldCallProcessarWithUUID() {

        // Arrange
        doNothing().when(processarPagamentoUseCase).processar(any(UUID.class));
        var param = "c08e8ca5-882c-4d8d-9cdf-0e1cd4351893";

        // Act
        pagamentoController.processarPagamento(param);

        // Assert
        verify(processarPagamentoUseCase).processar(UUID.fromString(param));

    }

}