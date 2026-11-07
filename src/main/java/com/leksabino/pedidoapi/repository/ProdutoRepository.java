package com.leksabino.pedidoapi.repository;
import com.leksabino.pedidoapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}