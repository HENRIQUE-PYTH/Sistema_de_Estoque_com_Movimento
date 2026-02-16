package com.sistema.estoque.movimentacao.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistema.estoque.movimentacao.entity.StatusMovimentacao.TipoMovimentacao;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "movimentos")
@JsonPropertyOrder({"id", "produto", "status"})
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao movimentacao;

    @Column(name = "quantidades")
    private Integer quantidade;

    public Movimentacao() {
    }

    public Movimentacao(Produto produto, TipoMovimentacao movimentacao, Integer quantidade) {
        this.produto = produto;
        this.movimentacao = movimentacao;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProdutos(Produto produto) {
        this.produto = produto;
    }

    public TipoMovimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(TipoMovimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movimentacao that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProduto(), that.getProduto()) && getMovimentacao() == that.getMovimentacao() && Objects.equals(getQuantidade(), that.getQuantidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduto(), getMovimentacao(), getQuantidade());
    }
}
