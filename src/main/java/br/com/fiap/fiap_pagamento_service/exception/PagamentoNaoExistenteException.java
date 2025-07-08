package br.com.fiap.fiap_pagamento_service.exception;

public class PagamentoNaoExistenteException extends RuntimeException {
    public PagamentoNaoExistenteException(String message) {
        super(message);
    }
}
