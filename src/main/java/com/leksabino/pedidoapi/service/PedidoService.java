package com.leksabino.pedidoapi.service;

import com.leksabino.pedidoapi.dto.PedidoRequest;
import com.leksabino.pedidoapi.model.*;
import com.leksabino.pedidoapi.repository.PedidoRepository;
import com.leksabino.pedidoapi.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProdutoRepository produtoRepo;

    public PedidoService(PedidoRepository pedidoRepo, ProdutoRepository produtoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.produtoRepo = produtoRepo;
    }

    @Transactional
    public Pedido criar(PedidoRequest req) {
        Pedido pedido = new Pedido();
        pedido.setCliente(req.getCliente());
        pedido.setStatus(StatusPedido.RECEBIDO);

        BigDecimal total = BigDecimal.ZERO;
        List<ItemPedido> itens = new ArrayList<>();

        for (PedidoRequest.ItemRequest itemReq : req.getItens()) {
            Produto produto = produtoRepo.findById(itemReq.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto nao encontrado: " + itemReq.getProdutoId()));

            BigDecimal precoUnitario = produto.getPreco();
            BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(itemReq.getQuantidade()))
                    .setScale(2, RoundingMode.HALF_UP);

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemReq.getQuantidade());
            item.setPrecoUnitario(precoUnitario);
            item.setSubtotal(subtotal);

            itens.add(item);
            total = total.add(subtotal);
        }

        pedido.setItens(itens);
        pedido.setTotal(total.setScale(2, RoundingMode.HALF_UP));

        return pedidoRepo.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepo.findAll();
    }
}