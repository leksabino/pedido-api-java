package com.leksabino.pedidoapi.service;
import com.leksabino.pedidoapi.dto.PedidoRequest;
import com.leksabino.pedidoapi.model.*;
import com.leksabino.pedidoapi.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class PedidoService {
    private final ProdutoRepository produtoRepo;
    private final PedidoRepository pedidoRepo;
    public PedidoService(ProdutoRepository produtoRepo, PedidoRepository pedidoRepo) {
        this.produtoRepo = produtoRepo;
        this.pedidoRepo = pedidoRepo;
    }
    @Transactional
    public Pedido criar(PedidoRequest req) {
        Pedido pedido = new Pedido();
        pedido.setCliente(req.getCliente());
        pedido.setStatus(StatusPedido.RECEBIDO);
        double total = 0;
        for (PedidoRequest.ItemRequest itemReq : req.getItens()) {
            Produto produto = produtoRepo.findById(itemReq.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemReq.getProdutoId()));
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemReq.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setSubtotal(produto.getPreco() * itemReq.getQuantidade());
            item.setPedido(pedido);
            total += item.getSubtotal();
            pedido.getItens().add(item);
        }
        pedido.setTotal(total);
        return pedidoRepo.save(pedido);
    }
    public List<Pedido> listar() { return pedidoRepo.findAll(); }
}
