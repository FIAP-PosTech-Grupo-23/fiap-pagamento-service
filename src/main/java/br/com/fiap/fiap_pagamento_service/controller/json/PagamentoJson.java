package br.com.fiap.fiap_pagamento_service.controller.json;

import java.math.BigDecimal;

public record PagamentoJson(
        BigDecimal valorTotal,
        PagamentoDetailsJson pagamento,
        String nome,
        String cpf,
        String endereco
) {
}
