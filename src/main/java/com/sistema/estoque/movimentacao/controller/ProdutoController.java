package com.sistema.estoque.movimentacao.controller;


import com.sistema.estoque.movimentacao.DTOs.ProdutoRequestDTO;
import com.sistema.estoque.movimentacao.DTOs.ProdutoResponseDTO;
import com.sistema.estoque.movimentacao.DTOs.QuantidadeDTO;
import com.sistema.estoque.movimentacao.entity.Produto;
import com.sistema.estoque.movimentacao.mapper.ProdutoMapper;
import com.sistema.estoque.movimentacao.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoService service, ProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ProdutoResponseDTO> findAll(){
        return service.findAllProducts()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){
        Produto produto = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> createProduct (@RequestBody ProdutoRequestDTO dto){
        Produto produto = mapper.toEntity(dto);
        Produto product = service.createProducts(produto);
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> updatingProducts(
            @PathVariable Long id,
            @RequestBody ProdutoRequestDTO dto){
        Produto produto1 = mapper.toEntity(dto);
        Produto produto = service.updateProducts(id, produto1);
       return ResponseEntity.ok(mapper.toResponse(produto));
    }

    @PutMapping("/status/{id}/inativar")
    public ResponseEntity<ProdutoResponseDTO> inativar(@PathVariable Long id){
        Produto produto = service.inativar(id);
        return ResponseEntity.ok(mapper.toResponse(produto));
    }

    @PutMapping("/status/{id}/ativar")
    public ResponseEntity<ProdutoResponseDTO> ativar(@PathVariable Long id){
        Produto produto = service.ativar(id);
        return ResponseEntity.ok(mapper.toResponse(produto));
    }

    @PostMapping("/produto/{id}/entradas")
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(
            @PathVariable Long id,
            @RequestBody QuantidadeDTO dto){
        Produto produto = service.adicionarProduto(id, dto.getQuantidade());
        return ResponseEntity.ok(mapper.toResponse(produto));
    }

    @PostMapping("/produto/{id}/saidas")
    public ResponseEntity<ProdutoResponseDTO> removerProduto(
            @PathVariable Long id,
            @RequestBody QuantidadeDTO dto){
        Produto produto = service.removerProduto(id, dto.getQuantidade());
        return ResponseEntity.ok(mapper.toResponse(produto));
    }
}
