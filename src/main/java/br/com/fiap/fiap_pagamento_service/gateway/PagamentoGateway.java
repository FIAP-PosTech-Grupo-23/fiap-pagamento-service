package br.com.fiap.fiap_pagamento_service.gateway;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;

public interface PagamentoGateway {
    Long salvar(Pagamento pagamento);
}
