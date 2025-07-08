package br.com.fiap.fiap_pagamento_service.usecase;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.domain.StatusPagamento;
import br.com.fiap.fiap_pagamento_service.gateway.PagamentoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.PedidoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.SistemaPagamentoExternoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessarPagamentoUseCase {

    private final SistemaPagamentoExternoGateway sistemaPagamentoExternoGateway;
    private final PagamentoGateway pagamentoGateway;
    private final PedidoGateway pedidoGateway;

    public void processar(UUID idSistemaExterno) {

        Pagamento pagamento = pagamentoGateway.buscaPorIdSistemaExterno(idSistemaExterno);

        StatusPagamento status = sistemaPagamentoExternoGateway.obterDados(idSistemaExterno);

        pedidoGateway.notificaPagamento(pagamento.getId(), status.name());
    }
}
