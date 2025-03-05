package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.PermissaoNaoEncontradaException;
import com.algafoodapi.domain.model.Permissao;
import com.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(()-> new PermissaoNaoEncontradaException(id));
    }

}
