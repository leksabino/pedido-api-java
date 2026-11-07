package com.leksabino.pedidoapi.dto;
import jakarta.validation.constraints.*;
import java.util.List;
public class PedidoRequest {
    @NotBlank private String cliente;
    @NotEmpty private List<ItemRequest> itens;
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public List<ItemRequest> getItens() { return itens; }
    public void setItens(List<ItemRequest> itens) { this.itens = itens; }
    public static class ItemRequest {
        @NotNull private Long produtoId;
        @Positive private int quantidade;
        public Long getProdutoId() { return produtoId; }
        public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    }
}
