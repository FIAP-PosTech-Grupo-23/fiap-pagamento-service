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
    private String numeroCartao;
    private String cvv;
    private String dataVencimento;
    private String nomeCobranca;
    private String cpfCobranca;
    private String enderecoCobranca;

    @Setter
    private UUID idSistemaExterno;

    public Pagamento(BigDecimal valor, String numeroCartao, String cvv, String dataVencimento, String nomeCobranca, String cpfCobranca, String enderecoCobranca) {
        this.valor = valor;
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.dataVencimento = dataVencimento;
        this.nomeCobranca = nomeCobranca;
        this.cpfCobranca = cpfCobranca;
        this.enderecoCobranca = enderecoCobranca;
    }

    public Pagamento(Long id, BigDecimal valor, String nomeCobranca, String cpfCobranca, String enderecoCobranca, UUID idSistemaExterno) {
        this.id = id;
        this.valor = valor;
        this.nomeCobranca = nomeCobranca;
        this.cpfCobranca = cpfCobranca;
        this.enderecoCobranca = enderecoCobranca;
        this.idSistemaExterno = idSistemaExterno;
    }
}
