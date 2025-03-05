package com.algafoodapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Pedido de codigo %s não foi encontrado", codigoPedido));
    }

}
