package com.algafood.algafoodapi.domain.exception;

public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
