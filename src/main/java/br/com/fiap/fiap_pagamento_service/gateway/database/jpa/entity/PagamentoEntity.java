package br.com.fiap.fiap_pagamento_service.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="pagamentos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private String nomeCobranca;
    private String cpfCobranca;
    private String enderecoCobranca;
    private UUID idSistemaExterno;

}
