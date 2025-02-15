package com.algafoodapi.domain.service;

import com.algafoodapi.api.model.UsuarioDTO;
import com.algafoodapi.api.model.input.UsuarioAlteraSenhaInput;
import com.algafoodapi.api.model.input.UsuarioAtualizarInput;
import com.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafoodapi.domain.exception.GrupoNaoEncontradoException;
import com.algafoodapi.domain.exception.SenhaInvalidaException;
import com.algafoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.algafoodapi.domain.model.Usuario;
import com.algafoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Service
public class CadastroUsuarioService {

    public static final String MSG_GRUPO_EM_USO = "O usuário de codigo %d não pode ser removido pois está em uso.";
    public static final String MSG_SENHA_DIFERENTE = "Senha atual informada não coincide com a senha do usuário";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario adicionar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, usuarioId));
        }
    }

    @Transactional
    public void alterarSenha(String senhaAtual, String novaSenha, Long usuarioId) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (!Objects.equals(usuario.getSenha(),senhaAtual)) {
            throw new SenhaInvalidaException(MSG_SENHA_DIFERENTE);
        }

        usuario.setSenha(novaSenha);
    }
}
