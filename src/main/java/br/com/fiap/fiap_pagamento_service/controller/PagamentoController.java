package br.com.fiap.fiap_pagamento_service.controller;

import br.com.fiap.fiap_pagamento_service.controller.json.PagamentoJson;
import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.usecase.EfetuarPagamentoUseCase;
import br.com.fiap.fiap_pagamento_service.usecase.ProcessarPagamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final EfetuarPagamentoUseCase efetuarPagamentoUseCase;
    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    @PostMapping
    public ResponseEntity<Long> novoPagamento(@RequestBody PagamentoJson pagamentoJson) {
        Pagamento pagamento = new Pagamento(pagamentoJson.valorTotal(), pagamentoJson.nome(), pagamentoJson.cpf(), pagamentoJson.endereco());
        return ResponseEntity.status(HttpStatus.CREATED).body(efetuarPagamentoUseCase.efetuar(pagamento));
    }

    @PutMapping("/{idSistemaExterno}")
    public ResponseEntity<Void> processarPagamento(@PathVariable String idSistemaExterno) {
        processarPagamentoUseCase.processar(UUID.fromString(idSistemaExterno));
        return ResponseEntity.ok().build();
    }

}
