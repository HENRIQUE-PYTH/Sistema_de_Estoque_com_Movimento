CREATE TABLE movimentos(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         produto_id BIGINT NOT NULL,
                         movimentacao VARCHAR(50) NOT NULL,
                         quantidades INT,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_movimento_produto
                             FOREIGN KEY (produto_id)
                             REFERENCES produtos(id)
);