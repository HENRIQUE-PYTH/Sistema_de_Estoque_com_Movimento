package com.sistema.estoque.movimentacao.exceptions;

public class ProdutoInativoException extends RuntimeException{
    public ProdutoInativoException (String msg){
        super(msg);
    }
}
