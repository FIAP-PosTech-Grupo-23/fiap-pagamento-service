package br.com.fiap.fiap_pagamento_service.controller.json;

public record PagamentoDetailsJson(
        String numeroCartao,
        String cvv,
        String dataVencimento
) {
}
