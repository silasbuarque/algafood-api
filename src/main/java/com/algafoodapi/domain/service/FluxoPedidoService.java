package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.Pedido;
import com.algafoodapi.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService cadastroPedido;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = cadastroPedido.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = cadastroPedido.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = cadastroPedido.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

}
