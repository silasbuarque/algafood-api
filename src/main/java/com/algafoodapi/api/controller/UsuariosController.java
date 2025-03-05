package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.UsuarioDTOAssembler;
import com.algafoodapi.api.assembler.UsuarioInputDisassembler;
import com.algafoodapi.api.model.UsuarioDTO;
import com.algafoodapi.api.model.input.UsuarioAdicionarInput;
import com.algafoodapi.api.model.input.UsuarioAlteraSenhaInput;
import com.algafoodapi.api.model.input.UsuarioAtualizarInput;
import com.algafoodapi.domain.exception.NegocioException;
import com.algafoodapi.domain.exception.SenhaInvalidaException;
import com.algafoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.algafoodapi.domain.model.Usuario;
import com.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioDTOAssembler.toListDTO(usuarioService.listar());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscarPorId(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return usuarioDTOAssembler.toDTO(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid UsuarioAdicionarInput usuarioInput) {
        try{
            Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
            return usuarioDTOAssembler.toDTO(usuarioService.adicionar(usuario));
        } catch (UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@RequestBody @Valid UsuarioAtualizarInput usuarioInput, @PathVariable Long usuarioId) {
        try {
            Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
            usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuario);
            return usuarioDTOAssembler.toDTO(usuarioService.adicionar(usuario));
        } catch (UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@RequestBody @Valid UsuarioAlteraSenhaInput senhaInput, @PathVariable Long usuarioId) {
        try {
            usuarioService.alterarSenha(senhaInput.getSenhaAtual(), senhaInput.getNovaSenha(), usuarioId);
        } catch (SenhaInvalidaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
