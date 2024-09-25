package com.buarque.algafood.domais.service;

import com.buarque.algafood.domais.exception.EntidadeEmUsoException;
import com.buarque.algafood.domais.exception.EntidadeNaoEncontradaException;
import com.buarque.algafood.domais.model.Cozinha;
import com.buarque.algafood.domais.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public void remover(Long cozinhaId) {
        try {
            cozinhaRepository.remover(cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                    "Cozinha de código %d nao pode ser removida, pois está em uso", cozinhaId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe um cadastro de cozinha com codigo %d", cozinhaId));
        }
    }

}
