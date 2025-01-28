package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafoodapi.domain.model.Cozinha;
import com.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

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
            throw new CozinhaNaoEncontradaException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, id));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new CozinhaNaoEncontradaException(cozinhaId));
    }

}
