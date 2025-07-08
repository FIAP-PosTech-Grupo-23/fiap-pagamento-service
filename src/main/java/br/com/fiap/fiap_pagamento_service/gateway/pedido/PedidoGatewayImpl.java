package br.com.fiap.fiap_pagamento_service.gateway.pedido;

import br.com.fiap.fiap_pagamento_service.gateway.PedidoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.pedido.client.PedidoClient;
import br.com.fiap.fiap_pagamento_service.gateway.pedido.json.NotificaPagamentoJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoClient pedidoClient;

    @Override
    public void notificaPagamento(Long idPagamento, String statusPagamento) {
        pedidoClient.notificaPagamento(new NotificaPagamentoJson(idPagamento, statusPagamento));
    }
}
