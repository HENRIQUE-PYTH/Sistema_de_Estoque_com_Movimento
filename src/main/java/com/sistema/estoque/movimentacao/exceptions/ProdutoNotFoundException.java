package com.sistema.estoque.movimentacao.exceptions;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String msg){
        super(msg);
    }
}
