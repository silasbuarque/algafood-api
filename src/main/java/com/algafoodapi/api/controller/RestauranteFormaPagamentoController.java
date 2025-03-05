package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.algafoodapi.api.model.FormaPagamentoDTO;
import com.algafoodapi.domain.model.Restaurante;
import com.algafoodapi.domain.service.CadastroFormaPagamentoService;
import com.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @GetMapping
    public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return formaPagamentoDTOAssembler.toCollectionsModel(restaurante.getFormasPagamento());
    }

    @DeleteMapping("/{formasPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formasPagamentoId) {
        formaPagamentoService.desassociarFormaPagamento(restauranteId, formasPagamentoId);
    }

    @PutMapping("/{formasPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formasPagamentoId) {
        formaPagamentoService.associarFormaPagamento(restauranteId, formasPagamentoId);
    }

}
