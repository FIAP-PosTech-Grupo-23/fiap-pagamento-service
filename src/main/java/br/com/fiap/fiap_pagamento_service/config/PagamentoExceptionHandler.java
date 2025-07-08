package br.com.fiap.fiap_pagamento_service.config;

import br.com.fiap.fiap_pagamento_service.exception.PagamentoNaoExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PagamentoExceptionHandler {

    @ExceptionHandler(PagamentoNaoExistenteException.class)
    public ResponseEntity<Object> handlerPagamentoNaoExistenteException(PagamentoNaoExistenteException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
