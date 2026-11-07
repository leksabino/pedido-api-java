package com.leksabino.pedidoapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoRequest {

    @NotBlank(message = "Cliente e obrigatorio")
    private String cliente;

    @NotEmpty(message = "Pedido precisa de pelo menos 1 item")
    @Valid
    private List<ItemRequest> itens;

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public List<ItemRequest> getItens() { return itens; }
    public void setItens(List<ItemRequest> itens) { this.itens = itens; }

    public static class ItemRequest {
        @NotNull(message = "produtoId e obrigatorio")
        private Long produtoId;

        @NotNull
        @Min(value = 1, message = "Quantidade deve ser no minimo 1")
        private Integer quantidade;

        public Long getProdutoId() { return produtoId; }
        public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    }
}