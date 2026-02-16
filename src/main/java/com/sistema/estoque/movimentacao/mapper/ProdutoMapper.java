package com.sistema.estoque.movimentacao.mapper;

import com.sistema.estoque.movimentacao.DTOs.ProdutoRequestDTO;
import com.sistema.estoque.movimentacao.DTOs.ProdutoResponseDTO;
import com.sistema.estoque.movimentacao.entity.Produto;
import org.springframework.stereotype.Service;

@Service
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO dto){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        return produto;
    }

    public ProdutoResponseDTO toResponse(Produto produto){
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());
        dto.setStatus(produto.getStatus());
        dto.setDataCriacao(produto.getDataCriacao());
        return dto;
    }
}
