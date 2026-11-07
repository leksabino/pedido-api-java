package com.leksabino.pedidoapi.controller;
import com.leksabino.pedidoapi.model.Produto;
import com.leksabino.pedidoapi.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository repo;
    public ProdutoController(ProdutoRepository repo) { this.repo = repo; }
    @PostMapping public Produto criar(@Valid @RequestBody Produto produto) { return repo.save(produto); }
    @GetMapping public List<Produto> listar() { return repo.findAll(); }
}