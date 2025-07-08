package br.com.fiap.fiap_pagamento_service.gateway;

public interface PedidoGateway {
    void notificaPagamento(Long idPagamento, String statusPagamento);
}
