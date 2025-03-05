package com.algafoodapi.domain.repository;

import com.algafoodapi.domain.model.Produto;

import java.util.Optional;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long> {
    Optional<Produto> findByRestauranteIdAndId(Long restauranteId, Long id);
}
