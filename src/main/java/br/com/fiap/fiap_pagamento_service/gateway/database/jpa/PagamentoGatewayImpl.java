package br.com.fiap.fiap_pagamento_service.gateway.database.jpa;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.gateway.PagamentoGateway;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.entity.PagamentoEntity;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
