package br.com.fiap.fiap_pagamento_service.gateway.database.jpa;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.exception.PagamentoNaoExistenteException;
import br.com.fiap.fiap_pagamento_service.gateway.PagamentoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.entity.PagamentoEntity;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoRepository pagamentoRepository;

    @Override
    public Long salvar(Pagamento pagamento) {

        PagamentoEntity pagamentoEntity = PagamentoEntity.builder()
                .valor(pagamento.getValor())
                .nomeCobranca(pagamento.getNomeCobranca())
                .cpfCobranca(pagamento.getCpfCobranca())
                .enderecoCobranca(pagamento.getEnderecoCobranca())
                .idSistemaExterno(pagamento.getIdSistemaExterno())
                .build();

        return pagamentoRepository.save(pagamentoEntity).getId();

    }

    @Override
    public Pagamento buscaPorIdSistemaExterno(UUID idSistemaExterno) {

        Optional<PagamentoEntity> pagamentoEntityOptional =
                pagamentoRepository.getByIdSistemaExterno(idSistemaExterno);

        if (pagamentoEntityOptional.isEmpty()) {
            throw new PagamentoNaoExistenteException("Pagamento não encontrado para esse Id. do sistema externo!");
        }

        PagamentoEntity pagamentoEntity = pagamentoEntityOptional.get();

        return new Pagamento(pagamentoEntity.getId(),
                pagamentoEntity.getValor(),
                pagamentoEntity.getNomeCobranca(),
                pagamentoEntity.getCpfCobranca(),
                pagamentoEntity.getEnderecoCobranca(),
                pagamentoEntity.getIdSistemaExterno());
    }
}
