package com.sistema.estoque.movimentacao.service;

import com.sistema.estoque.movimentacao.entity.Movimentacao;
import com.sistema.estoque.movimentacao.entity.Produto;
import com.sistema.estoque.movimentacao.entity.StatusMovimentacao.TipoMovimentacao;
import com.sistema.estoque.movimentacao.repository.MovimentacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovimentacaoService {

    private final Logger logger = LoggerFactory.getLogger(MovimentacaoService.class.getName());
    private final MovimentacaoRepository repository;

    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    public List<Movimentacao> listarTodas(){
        return repository.findAll();
    }

    public List<Movimentacao> ListarPorProdutos(Long id){
        return repository.findByProdutoId(id);
    }

    public void registrarEntrada(Produto produto, Integer quantidade) {
        logger.info("registering product entry");
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        produto.adicionarEstoque(quantidade);

        Movimentacao movimentacao = new Movimentacao(
                produto,
                TipoMovimentacao.ENTRADA,
                quantidade
        );
        logger.info("product registered sucessfully");
        produto.getMovimentacao().add(movimentacao);
        repository.save(movimentacao);
    }

    public void registrarSaida(Produto produto, Integer quantidade) {
        logger.info("registering product output");
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        produto.removerEstoque(quantidade);

        Movimentacao movimentacao = new Movimentacao(
                produto,
                TipoMovimentacao.SAIDA,
                quantidade
        );
        logger.info("Product shipment successfully registered.");
        produto.getMovimentacao().add(movimentacao);
        repository.save(movimentacao);
    }

}
