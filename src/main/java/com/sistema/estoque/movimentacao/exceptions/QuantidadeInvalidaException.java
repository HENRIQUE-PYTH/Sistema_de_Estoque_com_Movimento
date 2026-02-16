package com.sistema.estoque.movimentacao.exceptions;

public class QuantidadeInvalidaException extends RuntimeException{
    public QuantidadeInvalidaException(String msg){
        super(msg);
    }
}
