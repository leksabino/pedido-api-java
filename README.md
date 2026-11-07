# \# Pedido API Java

# 

# API REST para pedidos de restaurante. Versao Java do meu projeto `pedido-cli` em Python, agora com Spring Boot, JPA e validacao.

# 

# \## O que faz

# \- Cadastra produtos

# \- Cria pedidos com cálculo automatico do total

# \- Valida dados de entrada

# \- Lista produtos e pedidos

# 

# \## Stack

# \- Java 21

# \- Spring Boot 3.4.1

# \- Spring Web, Spring Data JPA, Validation

# \- H2 (banco em memoria)

# \- Maven, JUnit

# 

# \## Como rodar

# ./mvnw spring-boot:run

# 

# A API sobe em http://localhost:8080

# 

# \## Endpoints

# Criar produto: POST /produtos com {"nome":"X-Burger","preco":25.90}

# Listar produtos: GET /produtos

# Criar pedido: POST /pedidos com {"cliente":"Alex","itens":\[{"produtoId":1,"quantidade":2}]}

# Retorno esperado: "total": 51.8

# Listar pedidos: GET /pedidos

# 

# \## Regras de negocio

# \- Total = soma de preco \* quantidade

# \- Produto precisa de nome e preco maior que zero

# \- Pedido precisa de cliente e pelo menos 1 item

# \- Status: RECEBIDO, PREPARANDO, PRONTO, ENTREGUE, CANCELADO

