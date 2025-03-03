package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.PedidoDTOAssembler;
import com.algafoodapi.api.assembler.PedidoInputDisassembler;
import com.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import com.algafoodapi.api.model.PedidoDTO;
import com.algafoodapi.api.model.PedidoResumoDTO;
import com.algafoodapi.api.model.input.PedidoInput;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.Pedido;
import com.algafoodapi.domain.model.Usuario;
import com.algafoodapi.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {

            Pedido pedidoNovo = pedidoInputDisassembler.toModel(pedidoInput);

            pedidoNovo.setCliente(new Usuario());
            pedidoNovo.getCliente().setId(1L);

            pedidoNovo = pedidoService.emitir(pedidoNovo);

            return pedidoAssembler.toDTO(pedidoNovo);
        } catch (Exception e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
