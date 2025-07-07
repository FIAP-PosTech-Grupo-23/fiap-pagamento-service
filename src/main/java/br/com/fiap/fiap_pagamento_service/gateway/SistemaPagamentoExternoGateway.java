package br.com.fiap.fiap_pagamento_service.gateway;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;

import java.util.UUID;

public interface SistemaPagamentoExternoGateway {
    UUID solicitarPagamento(Pagamento pagamento);
}
