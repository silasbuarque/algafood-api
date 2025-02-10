package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.model.Estado;
import com.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCidadeService {

    public static final String MSG_ESTADO_EM_USO = "Estado de codigo %d não pode ser removido pois está em uso.";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService estadoService;

    @Transactional
    public Cidade salvar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}
