package com.algafoodapi.domain.repository;

import com.algafoodapi.domain.model.Produto;
import com.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long> {
    Optional<Produto> findByRestauranteIdAndId(Long restauranteId, Long id);

    List<Produto> findTodosByRestaurante(Restaurante restaurante);

    @Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
    List<Produto> findAtivosByRestaurante(Restaurante restaurante);
}
