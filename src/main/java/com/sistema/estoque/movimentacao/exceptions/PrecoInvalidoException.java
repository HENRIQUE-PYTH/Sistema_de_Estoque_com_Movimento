package com.sistema.estoque.movimentacao.exceptions;

public class PrecoInvalidoException extends RuntimeException{
    public PrecoInvalidoException(String msg){
        super(msg);
    }
}
