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
    public void confirmar(Long pedidoId) {

        Pedido pedido = cadastroPedido.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(
                    String.format("O status do pedido %d não pode alterar de %s para %s.",
                            pedidoId, pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());

    }

}
