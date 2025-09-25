-- Schema para transações financeiras
CREATE SCHEMA IF NOT EXISTS transaction_schema;

-- Tabela de contas
CREATE TABLE transaction_schema.account (
                                            id UUID PRIMARY KEY,
                                            owner UUID NOT NULL,
                                            created_at TIMESTAMP NOT NULL,
                                            status VARCHAR(20) NOT NULL CHECK (status IN ('ENABLED', 'DISABLED')),
                                            amount DECIMAL(15,2) NOT NULL,
                                            currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
                                            version INTEGER DEFAULT 0
);

-- Tabela de transações
CREATE TABLE transaction_schema.transaction (
                                                id UUID PRIMARY KEY,
                                                account_id UUID NOT NULL REFERENCES transaction_schema.account(id),
                                                type VARCHAR(10) NOT NULL CHECK (type IN ('CREDIT', 'DEBIT')),
                                                amount DECIMAL(15,2) NOT NULL,
                                                currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
                                                status VARCHAR(20) NOT NULL CHECK (status IN ('APPROVED', 'REJECTED', 'PENDING')),
                                                timestamp BIGINT NOT NULL,
                                                processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                version INTEGER DEFAULT 0
);

-- Índices para performance
CREATE INDEX idx_transaction_account_id ON transaction_schema.transaction(account_id);
CREATE INDEX idx_transaction_timestamp ON transaction_schema.transaction(timestamp);
CREATE INDEX idx_transaction_processed_at ON transaction_schema.transaction(processed_at);
CREATE INDEX idx_account_owner ON transaction_schema.account(owner);
CREATE INDEX idx_account_status ON transaction_schema.account(status);