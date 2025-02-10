package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.algafoodapi.api.assembler.CidadeInputDisassembler;
import com.algafoodapi.api.model.CidadeDTO;
import com.algafoodapi.api.model.input.CidadeInput;
import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.repository.CidadeRepository;
import com.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CidadeDTO> listar() {
        return cidadeDTOAssembler.toCollectionsModel(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        return cidadeDTOAssembler.toModel(cidadeService.buscarOuFalhar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            return cidadeDTOAssembler.toModel(cidadeService.salvar(cidade));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            return cidadeDTOAssembler.toModel(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.remover(cidadeId);
    }

}
