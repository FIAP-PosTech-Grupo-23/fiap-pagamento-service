package br.com.fiap.fiap_pagamento_service.gateway.pedido.client;

import br.com.fiap.fiap_pagamento_service.gateway.pedido.json.NotificaPagamentoJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pedido-client", url = "${pedido.api.url}")
public interface PedidoClient {

    @PutMapping
    void notificaPagamento(@RequestBody NotificaPagamentoJson notificaPagamentoJson);
}
