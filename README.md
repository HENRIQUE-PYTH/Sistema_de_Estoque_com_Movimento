📦 Sistema de Estoque com Movimentações

API REST desenvolvida para controle de produtos e movimentações de estoque.

O sistema permite cadastro de produtos, controle de status (ativo/inativo), entrada e saída de estoque e rastreamento completo das movimentações realizadas.

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🚀 Tecnologias Utilizadas

Java 21

Spring Boot

Spring Data JPA

Hibernate

Flyway (controle de versionamento do banco)

MySQL

Maven

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

Controller → Responsável pelos endpoints HTTP

Service → Contém as regras de negócio

Repository → Comunicação com o banco via JPA

DTO → Transferência de dados entre API e cliente

Mapper → Conversão entre Entity e DTO

Entity → Representação das tabelas do banco

Exception personalizada → Tratamento de erros de negócio

--------------------------------------------------------------------------------------------------------------------------------------------------------------

📌 Regras de Negócio

Produtos podem estar com status ATIVO ou INATIVO

Produtos INATIVOS:

❌ Não podem receber estoque

❌ Não podem remover estoque

Toda entrada ou saída gera um registro na tabela de movimentações

Movimentações são vinculadas a um produto via relacionamento @ManyToOne

O banco é versionado utilizando Flyway

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🗄️ Modelo de Dados (Resumo)
Produto

id

nome

descrição

preço

quantidade

status

data de criação

--------------------------------------------------------------------------------------------------------------------------------------------------------------

Movimentação

id

produto (ManyToOne)

tipo de movimentação (ENTRADA ou SAIDA)

quantidade

--------------------------------------------------------------------------------------------------------------------------------------------------------------

📡 Endpoints
📦 ProdutoController

Base path: /produtos

🔹 Listar todos os produtos

GET /produtos

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Buscar produto por ID

GET /produtos/{id}

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Criar novo produto

POST /produtos

Body:

{
  "nome": "Produto X",
  "descricao": "Descrição do produto",
  "preco": 100.00
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Atualizar produto

PUT /produtos/{id}

Body:

{
  "nome": "Novo nome",
  "descricao": "Nova descrição",
  "preco": 150.00
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Inativar produto

PUT /produtos/status/{id}/inativar

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Ativar produto

PUT /produtos/status/{id}/ativar

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Registrar entrada de estoque

POST /produtos/produto/{id}/entradas

Body:

{
  "quantidade": 10
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Registrar saída de estoque

POST /produtos/produto/{id}/saidas

Body:

{
  "quantidade": 5
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------

📊 MovimentacaoController

Base path: /movimentos

🔹 Listar todas as movimentações

GET /movimentos/movimentacoes

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔹 Listar movimentações por produto

GET /movimentos/produtos/{id}/movimentacoes

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🧪 Testes

Os endpoints podem ser testados utilizando:

Postman

Insomnia

Thunder Client (VSCode)

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🔄 Versionamento do Banco

O projeto utiliza Flyway para controle de versão do banco de dados.

As migrations ficam localizadas em:

src/main/resources/db/migration


Cada alteração estrutural no banco deve ser feita via script versionado.

--------------------------------------------------------------------------------------------------------------------------------------------------------------

🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

Aplicação prática de regras de negócio

Uso correto de relacionamento JPA

Controle de status com Enum

Separação de responsabilidades

Boas práticas em API REST

Versionamento de banco com Flyway
