package com.leksabino.pedidoapi.controller;

import com.leksabino.pedidoapi.dto.PedidoRequest;
import com.leksabino.pedidoapi.model.Pedido;
import com.leksabino.pedidoapi.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public Pedido criar(@Valid @RequestBody PedidoRequest req) {
        return service.criar(req);
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }
}