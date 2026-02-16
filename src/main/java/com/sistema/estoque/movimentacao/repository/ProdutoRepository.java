package com.sistema.estoque.movimentacao.repository;

import com.sistema.estoque.movimentacao.entity.Produto;
import com.sistema.estoque.movimentacao.entity.StatusMovimentacao.StatusProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByStatus(StatusProdutos status);
}
