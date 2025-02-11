package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.algafoodapi.api.assembler.FormaPagamentoInputDisassembler;
import com.algafoodapi.api.model.FormaPagamentoDTO;
import com.algafoodapi.api.model.input.FormaPagamentoInput;
import com.algafoodapi.domain.exception.FormaPagamentoNaoEncontradoException;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.FormaPagamento;
import com.algafoodapi.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/formaPagamento")
public class FormaPagamentoController {

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        return formaPagamentoDTOAssembler.toCollectionsModel(formaPagamentoService.listar());
    }

    @GetMapping("{formaPagamentoId}")
    public FormaPagamentoDTO buscarPorId(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        return formaPagamentoDTOAssembler.toDTO(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        try {
            FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

            return formaPagamentoDTOAssembler.toDTO(formaPagamentoService.salvar(formaPagamento));

        } catch (FormaPagamentoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput,
                                       @PathVariable Long formaPagamentoId) {
        try {
            FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

            formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamento);

            FormaPagamento formaPagamentoAtual = formaPagamentoService.salvar(formaPagamento);

            return formaPagamentoDTOAssembler.toDTO(formaPagamentoAtual);

        } catch (FormaPagamentoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.remover(formaPagamentoId);
    }
}
