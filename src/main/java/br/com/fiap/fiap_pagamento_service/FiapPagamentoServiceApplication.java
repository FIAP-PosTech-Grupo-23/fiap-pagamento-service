package br.com.fiap.fiap_pagamento_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FiapPagamentoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiapPagamentoServiceApplication.class, args);
	}

}
