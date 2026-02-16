package com.sistema.estoque.movimentacao.exceptions;

public class MovimentacaoNotFoundException extends RuntimeException{
    public MovimentacaoNotFoundException (String msg){
        super(msg);
    }
}
