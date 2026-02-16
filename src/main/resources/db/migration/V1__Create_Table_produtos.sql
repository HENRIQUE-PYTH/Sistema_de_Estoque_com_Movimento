CREATE TABLE produtos(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         descricoes VARCHAR(255),
                         preco DECIMAL(10,2),
                         quantidade INT,
                         status VARCHAR(50),
                         data_de_criacao DATE,
                         PRIMARY KEY (id)
);