package com.sistema.estoque.movimentacao.exceptions;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException (String msg){
        super(msg);
    }
}
