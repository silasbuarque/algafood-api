package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import com.algafoodapi.domain.model.Pedido;
import com.algafoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
}
