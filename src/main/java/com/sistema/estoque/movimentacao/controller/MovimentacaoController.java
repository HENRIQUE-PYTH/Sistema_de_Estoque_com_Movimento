package com.sistema.estoque.movimentacao.controller;

import com.sistema.estoque.movimentacao.DTOs.MovimentacaoResponseDTO;
import com.sistema.estoque.movimentacao.entity.Movimentacao;
import com.sistema.estoque.movimentacao.mapper.MovimentacaoMapper;
import com.sistema.estoque.movimentacao.mapper.ProdutoMapper;
import com.sistema.estoque.movimentacao.service.MovimentacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentacaoController {

    private final MovimentacaoService service;
    private final MovimentacaoMapper mapper;

    public MovimentacaoController(MovimentacaoService service, MovimentacaoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDTO>> listarTodas() {

        List<Movimentacao> lista = service.listarTodas();

        return ResponseEntity.ok(
                lista.stream()
                        .map(mapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/produtos/{id}/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDTO>> listarPorProduto(
            @PathVariable Long id) {

        List<Movimentacao> lista = service.ListarPorProdutos(id);

        return ResponseEntity.ok(
                lista.stream()
                        .map(mapper::toResponse)
                        .toList()
        );
    }

}
