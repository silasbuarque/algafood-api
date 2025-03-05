package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafoodapi.domain.exception.GrupoNaoEncontradoException;
import com.algafoodapi.domain.model.Grupo;
import com.algafoodapi.domain.model.Permissao;
import com.algafoodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroGrupoService {

    public static final String MSG_GRUPO_EM_USO = "Grupo de codigo %d não pode ser removido pois está em uso.";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscarOuFalhar(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(()-> new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
        }
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        grupo.getPermissoes().remove(permissao);
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        grupo.getPermissoes().add(permissao);
    }
}
