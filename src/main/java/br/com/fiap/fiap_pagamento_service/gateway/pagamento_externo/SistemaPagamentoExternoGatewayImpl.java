package br.com.fiap.fiap_pagamento_service.gateway.pagamento_externo;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.domain.StatusPagamento;
import br.com.fiap.fiap_pagamento_service.gateway.SistemaPagamentoExternoGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SistemaPagamentoExternoGatewayImpl implements SistemaPagamentoExternoGateway {

    @Override
    public UUID solicitarPagamento(Pagamento pagamento) {
        return UUID.randomUUID();
    }

    @Override
    public StatusPagamento obterDados(UUID id) {
        var random = Math.random() * 100; // 0.0 a 99.9
        if (random <= 80.0) {
            return StatusPagamento.APROVADO;
        } else {
            return StatusPagamento.REPROVADO;
        }
    }
}
