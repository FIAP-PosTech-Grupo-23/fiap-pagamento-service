package br.com.fiap.fiap_pagamento_service.usecase;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.gateway.PagamentoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.SistemaPagamentoExternoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EfetuarPagamentoUseCase {

    private final SistemaPagamentoExternoGateway sistemaPagamentoExternoGateway;
    private final PagamentoGateway pagamentoGateway;

    public Long efetuar(Pagamento pagamento) {

        UUID idSistemaExterno = sistemaPagamentoExternoGateway.solicitarPagamento(pagamento);
        pagamento.setIdSistemaExterno(idSistemaExterno);

        return pagamentoGateway.salvar(pagamento);

    }
}
