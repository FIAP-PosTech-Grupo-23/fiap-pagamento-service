package br.com.fiap.fiap_pagamento_service.gateway.pagamento_externo;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.gateway.SistemaPagamentoExternoGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SistemaPagamentoExternoGatewayImpl implements SistemaPagamentoExternoGateway {

    @Override
    public UUID solicitarPagamento(Pagamento pagamento) {
        return UUID.randomUUID();
    }
}
