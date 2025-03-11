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
import com.algafoodapi.domain.repository.filter.PedidoFilter;
import com.algafoodapi.domain.service.EmissaoPedidoService;
import com.algafoodapi.infrestructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private EmissaoPedidoService pedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoAssembler;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;


//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoService.listar();
//        List<PedidoResumoDTO> pedidosDTO = pedidoResumoAssembler.toListDTO(pedidos);
//
//        MappingJacksonValue wrapperPedidos = new MappingJacksonValue(pedidosDTO);
//
//        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
//        simpleFilterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if (StringUtils.isNotBlank(campos)) {
//            simpleFilterProvider.addFilter("pedidoFilter",
//                    SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        wrapperPedidos.setFilters(simpleFilterProvider);
//
//        return wrapperPedidos;
//    }

    @GetMapping
    public List<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter) {
        List<Pedido> listar = pedidoService.pesquisar(pedidoFilter);

        return pedidoResumoAssembler.toListDTO(listar);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
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
