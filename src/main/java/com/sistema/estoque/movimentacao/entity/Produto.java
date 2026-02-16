package com.sistema.estoque.movimentacao.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistema.estoque.movimentacao.entity.StatusMovimentacao.StatusProdutos;
import com.sistema.estoque.movimentacao.exceptions.QuantidadeInvalidaException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produtos")
@JsonPropertyOrder({
        "id",
        "nome",
        "descricao",
        "preco",
        "quantidade",
        "status",
        "dataCriacao"
})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 125, unique = true)
    private String nome;

    @Column(name = "Descricoes")
    private String descricao;

    @Column (nullable = false)
    private Double preco;

    @Column (nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private StatusProdutos status;

    @Column (name = "Data_de_criacao")
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Movimentacao> movimentacao = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, String descricao, Double preco, Integer quantidade, StatusProdutos status) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.status = status;
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public StatusProdutos getStatus() {
        return status;
    }

    public void setStatus(StatusProdutos status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(List<Movimentacao> movimentacao) {
        this.movimentacao = movimentacao;
    }

    public void adicionarEstoque (Integer quantidade){
        if (quantidade < 0) throw  new QuantidadeInvalidaException("The quantity must have a positive value.");
        this.quantidade += quantidade;
    }

    public void removerEstoque(Integer quantidade){
        if (quantidade < 0) throw new QuantidadeInvalidaException("The quantity must have a positive value.");
        if (this.quantidade < quantidade) throw new IllegalStateException();
        this.quantidade -= quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
