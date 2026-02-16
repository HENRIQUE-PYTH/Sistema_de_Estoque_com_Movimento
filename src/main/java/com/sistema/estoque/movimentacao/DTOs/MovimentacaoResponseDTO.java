package com.sistema.estoque.movimentacao.DTOs;

public class MovimentacaoResponseDTO {
    private Long id;
    private Long produtoId;
    private String tipo;
    private Integer quantidade;

    public MovimentacaoResponseDTO(Long id, Long produtoId, String tipo, Integer quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
