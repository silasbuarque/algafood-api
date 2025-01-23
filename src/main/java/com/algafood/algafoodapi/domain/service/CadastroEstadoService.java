package com.algafood.algafoodapi.domain.service;

import com.algafood.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algafood.algafoodapi.domain.model.Estado;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado de id %d não foi encontrado";
    public static final String MSG_ESTADO_EM_USO = "Estado de codigo %d não pode ser removido pois está em uso.";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
        }
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException( estadoId));
    }
}
