package com.leksabino.pedidoapi.service;

import com.leksabino.pedidoapi.dto.PedidoRequest;
import com.leksabino.pedidoapi.model.Pedido;
import com.leksabino.pedidoapi.model.Produto;
import com.leksabino.pedidoapi.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PedidoServiceTest {

    @Autowired
    private PedidoService service;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Test
    void deveCalcularTotalDoPedido() {
        Produto produto = new Produto();
        produto.setNome("X-Burger");
        produto.setPreco(new BigDecimal("25.90"));
        produto = produtoRepo.save(produto);

        PedidoRequest req = new PedidoRequest();
        req.setCliente("Alex");

        PedidoRequest.ItemRequest item = new PedidoRequest.ItemRequest();
        item.setProdutoId(produto.getId());
        item.setQuantidade(2);
        req.setItens(List.of(item));

        Pedido pedido = service.criar(req);

        assertEquals(new BigDecimal("51.80"), pedido.getTotal());
    }
}
