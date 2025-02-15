package com.algafoodapi.domain.exception;

public class SenhaInvalidaException extends NegocioException{

    private static final long serialVersionUID = 1L;

    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }

    public SenhaInvalidaException(Long id) {
        this(String.format("Usuario de id %d não foi encontrado", id));
    }
}