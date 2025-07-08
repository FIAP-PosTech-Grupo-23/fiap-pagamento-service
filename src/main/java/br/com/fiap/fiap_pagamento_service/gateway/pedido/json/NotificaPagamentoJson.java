package br.com.fiap.fiap_pagamento_service.gateway.pedido.json;

public record NotificaPagamentoJson(
        Long idPagamento,
        String statusPagamento
) {
}
