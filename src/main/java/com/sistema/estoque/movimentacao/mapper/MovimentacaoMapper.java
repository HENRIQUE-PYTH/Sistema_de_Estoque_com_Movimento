package com.sistema.estoque.movimentacao.mapper;

import com.sistema.estoque.movimentacao.DTOs.MovimentacaoResponseDTO;
import com.sistema.estoque.movimentacao.entity.Movimentacao;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoMapper {

    public MovimentacaoResponseDTO toResponse(Movimentacao movimentacao) {

        if (movimentacao == null) {
            return null;
        }

        return new MovimentacaoResponseDTO(
                movimentacao.getId(),
                movimentacao.getProduto().getId(),
                movimentacao.getMovimentacao().name(),
                movimentacao.getQuantidade()
        );
    }
}
