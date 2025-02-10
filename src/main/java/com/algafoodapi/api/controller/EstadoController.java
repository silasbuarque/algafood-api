package com.algafoodapi.api.controller;

import java.util.List;

import com.algafoodapi.api.assembler.EstadoDTOAssembler;
import com.algafoodapi.api.assembler.EstadoInputDisassembler;
import com.algafoodapi.api.model.EstadoDTO;
import com.algafoodapi.api.model.input.EstadoInput;
import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.Estado;
import com.algafoodapi.domain.repository.EstadoRepository;
import com.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoDTOAssembler estadoDTOAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoDTO> listar() {
        return estadoDTOAssembler.toCollectionsModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return estadoDTOAssembler.toDTO(cadastroEstado.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        try {
            Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
            return estadoDTOAssembler.toDTO(cadastroEstado.salvar(estado));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {

        try {
            Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
            estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
            return estadoDTOAssembler.toDTO(cadastroEstado.salvar(estadoAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.remover(estadoId);
    }

}