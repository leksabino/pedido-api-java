# Pedido API Java

API REST para pedidos de restaurante. É a versão em Java do meu projeto [pedido-cli](https://github.com/leksabino/pedido-cli), agora com Spring Boot, JPA e validação.

## O que faz

- Cadastra produtos
- Cria pedidos com cálculo automático do total
- Valida os dados de entrada
- Lista produtos e pedidos

## Stack

- Java 21
- Spring Boot 3.4.1 (Web, Data JPA, Validation)
- H2 (banco em memória)
- Maven e JUnit

## Como rodar

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | `/produtos` | Cria um produto |
| GET | `/produtos` | Lista os produtos |
| POST | `/pedidos` | Cria um pedido |
| GET | `/pedidos` | Lista os pedidos |

Exemplo de produto:

```json
{"nome": "X-Burger", "preco": 25.90}
```

Exemplo de pedido (supondo que o produto de id 1 seja o X-Burger):

```json
{"cliente": "Maria", "itens": [{"produtoId": 1, "quantidade": 2}]}
```

O total retornado é `51.80`.

## Regras de negócio

- Total = soma de preço × quantidade
- O valor é calculado com `BigDecimal`, para evitar erro de arredondamento em dinheiro
- Produto precisa de nome e de preço maior que zero
- Pedido precisa de cliente e de pelo menos 1 item
- Status do pedido: RECEBIDO, PREPARANDO, PRONTO, ENTREGUE, CANCELADO

## Limitações

- O banco H2 fica em memória: os dados se perdem quando a aplicação para.
- Não há autenticação.
