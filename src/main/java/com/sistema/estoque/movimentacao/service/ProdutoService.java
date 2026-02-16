package com.sistema.estoque.movimentacao.service;

import com.sistema.estoque.movimentacao.entity.Produto;
import com.sistema.estoque.movimentacao.entity.StatusMovimentacao.StatusProdutos;
import com.sistema.estoque.movimentacao.exceptions.PrecoInvalidoException;
import com.sistema.estoque.movimentacao.exceptions.ProdutoNotFoundException;
import com.sistema.estoque.movimentacao.exceptions.QuantidadeInvalidaException;
import com.sistema.estoque.movimentacao.exceptions.RegraDeNegocioException;
import com.sistema.estoque.movimentacao.mapper.ProdutoMapper;
import com.sistema.estoque.movimentacao.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(ProdutoService.class.getName());
    private final ProdutoRepository repository;
    private final MovimentacaoService service;


    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper, MovimentacaoService service) {
        this.repository = repository;
        this.mapper = mapper;
        this.service = service;
    }

    public List<Produto> findAllProducts(){
        return repository.findByStatus(StatusProdutos.ATIVO);
    }

    public Produto findById (Long id){
        logger.info("Finding one product");
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("product not found"));
        return produto;
    }

    public Produto createProducts(Produto produto){
        logger.info("performing product validations");
        if (produto.getNome() == null || produto.getNome().isBlank()){
            throw new RegraDeNegocioException("Product Name Required");
        }
        if (produto.getPreco() == null || produto.getPreco() < 0){
            throw new PrecoInvalidoException("invalid price");
        }
        if (produto.getQuantidade() < 0){
            throw new QuantidadeInvalidaException("invalid quantity");
        }
        Produto product = new Produto(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidade(),
                StatusProdutos.ATIVO);
        logger.info("Product create on sucessfull");
        return repository.save(product);
    }

    public Produto updateProducts (Long id, Produto update){
        logger.info("Searching for the product");
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("product not found"));
        logger.info("product found");
        produto.setNome(update.getNome());
        produto.setDescricao(update.getDescricao());
        produto.setPreco(update.getPreco());
        logger.info("Product updated successfully");
        return repository.save(produto);
    }

    public Produto inativar(Long id){
        logger.info("Looking for the product to deactivate it.");
        Produto produto = repository.findById(id)
                .orElseThrow(()-> new ProdutoNotFoundException("product not found"));
        produto.setStatus(StatusProdutos.INATIVO);
        return repository.save(produto);
    }

    public Produto ativar(Long id){
        logger.info("Looking for the product to activate it.");
        Produto produto = repository.findById(id)
                .orElseThrow(()-> new ProdutoNotFoundException("product not found"));
        produto.setStatus(StatusProdutos.ATIVO);
        return repository.save(produto);
    }

    @Transactional
    public Produto adicionarProduto (Long id, Integer quantidade){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Product not found"));
        if (produto.getStatus() != StatusProdutos.ATIVO){
            throw new IllegalArgumentException("Inactive product cannot receive stock");
        }
        service.registrarEntrada(produto, quantidade);
        return  produto;
    }

    @Transactional
    public Produto removerProduto (Long id, Integer quantidade){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("product not found"));
        if (produto.getStatus() != StatusProdutos.ATIVO){
            throw new IllegalArgumentException("Inactive product cannot remove stock");
        }
        service.registrarSaida(produto, quantidade);
        return produto;
    }

}
