package com.algafoodapi.api.controller;

import java.util.List;

import com.algafoodapi.api.assembler.CozinhaDTOAssembler;
import com.algafoodapi.api.assembler.CozinhaInputDisassembler;
import com.algafoodapi.api.model.CozinhaDTO;
import com.algafoodapi.api.model.input.CozinhaInput;
import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.Cidade;
import org.springframework.beans.BeanUtils;
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

import com.algafoodapi.domain.model.Cozinha;
import com.algafoodapi.domain.repository.CozinhaRepository;
import com.algafoodapi.domain.service.CadastroCozinhaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaDTOAssembler cozinhaDTOAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;


    @GetMapping
    public List<CozinhaDTO> listar() {
        return cozinhaDTOAssembler.toCollectionsDTO(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        return cozinhaDTOAssembler.toDTO(cadastroCozinha.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        try {
            Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
            return cozinhaDTOAssembler.toDTO(cadastroCozinha.salvar(cozinha));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        try {
            Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
            cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
            return cozinhaDTOAssembler.toDTO(cadastroCozinha.salvar(cozinhaAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinha.remover(cozinhaId);
    }

}