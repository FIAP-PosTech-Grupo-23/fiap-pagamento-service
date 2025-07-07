package br.com.fiap.fiap_pagamento_service.gateway.database.jpa.repository;

import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
}
