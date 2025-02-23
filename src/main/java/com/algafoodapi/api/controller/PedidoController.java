package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.PedidoDTOAssembler;
import com.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import com.algafoodapi.api.model.PedidoDTO;
import com.algafoodapi.api.model.PedidoResumoDTO;
import com.algafoodapi.domain.model.Pedido;
import com.algafoodapi.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService pedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoAssembler;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoAssembler;

    @GetMapping
    public List<PedidoResumoDTO> listar() {
        List<Pedido> listar = pedidoService.listar();

        return pedidoResumoAssembler.toListDTO(listar);
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        return pedidoAssembler.toDTO(pedido);
    }

}
