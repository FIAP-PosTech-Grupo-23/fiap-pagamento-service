package br.com.fiap.fiap_pagamento_service.gateway;

import br.com.fiap.fiap_pagamento_service.domain.Pagamento;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.PagamentoGatewayImpl;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.entity.PagamentoEntity;
import br.com.fiap.fiap_pagamento_service.gateway.database.jpa.repository.PagamentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PagamentoGatewayTest {

    AutoCloseable mock;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoGatewayImpl pagamentoGateway;

    private final Pagamento pagamento = new Pagamento(BigDecimal.valueOf(5000L),
            "5189 7498 3445 2546",
            "123",
            "03/2027",
            "Guilherme Lima",
            "786.897.260-20",
            "Rua Fiap, n. 123");

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldReceivePagamentoAndSaveItsEntity() {

        // Arrange
        pagamento.setIdSistemaExterno(UUID.randomUUID());

        PagamentoEntity pagamentoEntity = PagamentoEntity.builder()
                .valor(pagamento.getValor())
                .nomeCobranca(pagamento.getNomeCobranca())
                .cpfCobranca(pagamento.getCpfCobranca())
                .enderecoCobranca(pagamento.getEnderecoCobranca())
                .idSistemaExterno(pagamento.getIdSistemaExterno())
                .build();

        doReturn(pagamentoEntity).when(pagamentoRepository).save(any(PagamentoEntity.class));

        // Act
        pagamentoGateway.salvar(pagamento);

        // Assert
        ArgumentCaptor<PagamentoEntity> captor = ArgumentCaptor.forClass(PagamentoEntity.class);
        verify(pagamentoRepository).save(captor.capture());
        PagamentoEntity pagamentoEntityCaptured = captor.getValue();
        assertEquals(pagamento.getIdSistemaExterno(), pagamentoEntityCaptured.getIdSistemaExterno());
    }

    @Test
    void shouldReturnPagamentoByIdSistemaExterno() {

        // Arrange
        UUID idSistemaExterno = UUID.randomUUID();
        pagamento.setIdSistemaExterno(idSistemaExterno);

        Optional<PagamentoEntity> pagamentoEntityOptional = Optional.of(PagamentoEntity.builder()
                .valor(pagamento.getValor())
                .nomeCobranca(pagamento.getNomeCobranca())
                .cpfCobranca(pagamento.getCpfCobranca())
                .enderecoCobranca(pagamento.getEnderecoCobranca())
                .idSistemaExterno(pagamento.getIdSistemaExterno())
                .build());

        doReturn(pagamentoEntityOptional).when(pagamentoRepository).getByIdSistemaExterno(idSistemaExterno);

        // Act
        Pagamento pagamentoResponse = pagamentoGateway.buscaPorIdSistemaExterno(idSistemaExterno);

        // Assert
        assertEquals(pagamentoResponse.getIdSistemaExterno(), idSistemaExterno);
    }

}