package com.algafoodapi.api.controller;

import com.algafoodapi.api.assembler.GrupoDTOAssembler;
import com.algafoodapi.api.model.GrupoDTO;
import com.algafoodapi.domain.model.Grupo;
import com.algafoodapi.domain.model.Usuario;
import com.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        return grupoDTOAssembler.toListDTO(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }

}

