package com.algafoodapi.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PermissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long id) {
        this(String.format("Permissao de id %d não foi encontrado", id));
    }

}
