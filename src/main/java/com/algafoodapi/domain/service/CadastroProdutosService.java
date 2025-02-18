package com.algafoodapi.domain.service;

import com.algafoodapi.domain.exception.ProdutoNaoEncontradoException;
import com.algafoodapi.domain.model.Produto;
import com.algafoodapi.domain.model.Restaurante;
import com.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutosService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findByRestauranteIdAndId(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, restauranteId));
    }

    @Transactional
    public Produto adicionar(Produto produto) {
        return produtoRepository.save(produto);
    }

}
