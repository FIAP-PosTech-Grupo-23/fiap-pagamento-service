CREATE TABLE pagamentos (
    id SERIAL PRIMARY KEY,
    valor NUMERIC(10,2) NOT NULL,
    nome_cobranca VARCHAR(200) NOT NULL,
    cpf_cobranca VARCHAR(14) NOT NULL,
    endereco_cobranca VARCHAR(200) NOT NULL,
    id_sistema_externo UUID NOT NULL
);
