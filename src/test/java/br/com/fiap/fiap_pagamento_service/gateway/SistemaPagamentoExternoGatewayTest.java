package br.com.fiap.fiap_pagamento_service.gateway;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.domain.StatusPagamento;
import br.com.fiap.fiap_pagamento_service.gateway.pagamento_externo.SistemaPagamentoExternoGatewayImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SistemaPagamentoExternoGatewayTest {

    private final SistemaPagamentoExternoGatewayImpl sistemaPagamentoExternoGateway;

    public SistemaPagamentoExternoGatewayTest() {
        sistemaPagamentoExternoGateway = new SistemaPagamentoExternoGatewayImpl();
    }

    @Test
    void shouldReceivePagamentoAndReturnAUUID() {

        // Arrange
        Pagamento pagamento = new Pagamento(BigDecimal.valueOf(5000L),
                "5189 7498 3445 2546",
                "123",
                "03/2027",
                "Guilherme Lima",
                "786.897.260-20",
                "Rua Fiap, n. 123");

        // Act
        var res = sistemaPagamentoExternoGateway.solicitarPagamento(pagamento);

        // Assert
        assertInstanceOf(UUID.class, res);
    }

    @Test
    void shouldReturnStatusByIdSistemaExterno() {

        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        var res = sistemaPagamentoExternoGateway.obterDados(uuid);

        // Assert
        assertTrue(Arrays.stream(StatusPagamento.values()).toList().contains(res));
    }

}