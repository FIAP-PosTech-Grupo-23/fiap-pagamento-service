package br.com.fiap.fiap_pagamento_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Pagamento {

    private Long id;
    private BigDecimal valor;
    private String nomeCobranca;
    private String cpfCobranca;
    private String enderecoCobranca;

    @Setter
    private UUID idSistemaExterno;

    public Pagamento(BigDecimal valor, String nomeCobranca, String cpfCobranca, String enderecoCobranca) {
        this.valor = valor;
        this.nomeCobranca = nomeCobranca;
        this.cpfCobranca = cpfCobranca;
        this.enderecoCobranca = enderecoCobranca;
    }

}
