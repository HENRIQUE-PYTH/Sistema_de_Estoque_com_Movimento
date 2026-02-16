package com.sistema.estoque.movimentacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProdutoNotFound(ProdutoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage() ));
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErrorResponse>handleRegraDeNegocio(RegraDeNegocioException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(403, ex.getMessage()));
    }

    @ExceptionHandler(PrecoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handlePrecoInvalido (PrecoInvalidoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(403, ex.getMessage()));

    }

    @ExceptionHandler(QuantidadeInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleQuantidadeInvalida(QuantidadeInvalidaException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(403, ex.getMessage()));
    }

    @ExceptionHandler(MovimentacaoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMovimentacaoNotFound(MovimentacaoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }

    @ExceptionHandler(ProdutoInativoException.class)
    public ResponseEntity<ErrorResponse> handleProdutoInativo(ProdutoInativoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(403, ex.getMessage()));
    }
}
