package com.algafood.algafoodapi.domain.service;

import com.algafood.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe um cozinha com o id %d%n";
    public static final String MSG_ENTIDADE_EM_USO = "Cozinha de codigo %d não pode ser removida pois está em uso.";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, id));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cozinhaId))
        );
    }

}
