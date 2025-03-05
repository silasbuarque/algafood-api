package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.GrupoDTOAssembler;
import com.algafoodapi.api.assembler.GrupoInputDisassembler;
import com.algafoodapi.api.model.GrupoDTO;
import com.algafoodapi.api.model.input.GrupoInput;
import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.exception.GrupoNaoEncontradoException;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.model.Grupo;
import com.algafoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private CadastroGrupoService grupoService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoDTO> listar() {
        return grupoDTOAssembler.toListDTO(grupoService.listar());
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        return grupoDTOAssembler.toDTO(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        try {
            Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
            return grupoDTOAssembler.toDTO(grupoService.salvar(grupo));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        try {
            Grupo grupo = grupoService.buscarOuFalhar(grupoId);
            grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);
            return grupoDTOAssembler.toDTO(grupoService.salvar(grupo));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }


}
